/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2021 Engineering Ingegneria Informatica S.p.A.
 *
 * Knowage is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Knowage is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.eng.knowage.knowageapi.service.impl;

import static java.util.stream.Collectors.toList;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.eng.knowage.boot.error.InvalidHtmlPayloadException;
import it.eng.knowage.boot.error.KnowageRuntimeException;
import it.eng.knowage.boot.filter.XSSUtils;
import it.eng.knowage.knowageapi.dao.SbiWidgetGalleryDao;
import it.eng.knowage.knowageapi.dao.dto.SbiWidgetGallery;
import it.eng.knowage.knowageapi.dao.dto.SbiWidgetGalleryTag;
import it.eng.knowage.knowageapi.dao.dto.SbiWidgetGalleryTagId;
import it.eng.knowage.knowageapi.resource.dto.Code;
import it.eng.knowage.knowageapi.resource.dto.WidgetGalleryDTO;
import it.eng.knowage.knowageapi.service.WidgetGalleryAPI;
import it.eng.spagobi.services.security.SpagoBIUserProfile;

@Component
public class WidgetGalleryAPIimpl implements WidgetGalleryAPI {

	private static Logger logger = Logger.getLogger(WidgetGalleryAPIimpl.class);

	@Autowired
	private SbiWidgetGalleryDao sbiWidgetGalleryDao;

	private static final String GALLERY_FUNCTION = "WidgetGalleryManagement";

	private final XSSUtils xssUtils = new XSSUtils();

	/**
	 * This method gets all widgets within all tenants
	 */
	@Override
	public List<WidgetGalleryDTO> getWidgets() throws JSONException {
		return sbiWidgetGalleryDao.findAll().stream().collect(toList());
	}

	/**
	 * This method gets all widgets related the tenant set into profile
	 */
	@Override
	public List<WidgetGalleryDTO> getWidgetsByTenant(SpagoBIUserProfile profile) throws JSONException {
		List<WidgetGalleryDTO> ret = null;
		if (this.canSeeGallery(profile)) {
			ret = sbiWidgetGalleryDao.findAllByTenant(profile.getOrganization()).stream().collect(toList());
		}

		return ret;
	}

	/**
	 * This method gets widget with @{id} within the tenant set into profile, or null
	 */
	@Override
	public WidgetGalleryDTO getWidgetsById(String id, SpagoBIUserProfile profile) throws JSONException {
		WidgetGalleryDTO widget = null;
		if (this.canSeeGallery(profile)) {
			widget = sbiWidgetGalleryDao.findByIdTenant(id, profile.getOrganization());
		}

		return widget;
	}

	@Override
	public WidgetGalleryDTO makeNewWidget(WidgetGalleryDTO widgetGalleryDTO, SpagoBIUserProfile profile, boolean create) {
		if (this.canSeeGallery(profile)) {
			if (create) {
				widgetGalleryDTO.setId(UUID.randomUUID().toString());
			}

			// Validating CODES with whitelist
			Code code = widgetGalleryDTO.getCode();
			try {
				String html = code.getHtml();

				checkXSS(html);

				String htmlCode = html;
				JSONObject jsonBody = new JSONObject(widgetGalleryDTO.getTemplate());
				JSONObject jsonCode = jsonBody.optJSONObject("code");
				jsonCode.put("html", htmlCode);
				jsonCode.put("python", widgetGalleryDTO.getCode().getPython());
				jsonCode.put("css", widgetGalleryDTO.getCode().getCss());
				jsonCode.put("javascript", widgetGalleryDTO.getCode().getJavascript());
				jsonBody.put("code", jsonCode);
				widgetGalleryDTO.setTemplate(jsonBody.toString());
			} catch (JSONException e) {
				throw new KnowageRuntimeException(e.getMessage());
			}

			SbiWidgetGallery newSbiWidgetGallery = new SbiWidgetGallery();
			newSbiWidgetGallery.getId().setUuid(widgetGalleryDTO.getId());
			newSbiWidgetGallery.setAuthor(profile.getUserId());
			newSbiWidgetGallery.setDescription(widgetGalleryDTO.getDescription());
			newSbiWidgetGallery.setName(widgetGalleryDTO.getName());
			newSbiWidgetGallery.setLabel(widgetGalleryDTO.getLabel());
			newSbiWidgetGallery.getId().setOrganization(profile.getOrganization());
			newSbiWidgetGallery.setPreviewImage(widgetGalleryDTO.getImage() != null ? widgetGalleryDTO.getImage().getBytes() : "".getBytes());
			newSbiWidgetGallery.setSbiVersionIn("");
			newSbiWidgetGallery.setTemplate(widgetGalleryDTO.getTemplate().getBytes());
			newSbiWidgetGallery.setTimeIn(Timestamp.from(Instant.now()));
			newSbiWidgetGallery.setType(widgetGalleryDTO.getType());
			newSbiWidgetGallery.setUserIn(profile.getUserId());
			newSbiWidgetGallery.setUsageCounter(1);
			newSbiWidgetGallery.setOutputType(widgetGalleryDTO.getOutputType());

			String tags = widgetGalleryDTO.getTags().toString().equals("[]") ? "" : widgetGalleryDTO.getTags().toString();
			List<SbiWidgetGalleryTag> tagList = createNewWidgetTagsByList(newSbiWidgetGallery, profile.getUserId(), tags);
			if (tagList != null) {
				newSbiWidgetGallery.getSbiWidgetGalleryTags().addAll(tagList);
			}
			sbiWidgetGalleryDao.create(newSbiWidgetGallery);
		}
		return widgetGalleryDTO;
	}

	public WidgetGalleryDTO importNewWidget(WidgetGalleryDTO widgetGalleryDTO, SpagoBIUserProfile profile) {
		/* The import procedure must also manage widgets with the id field not filled in */
		return makeNewWidget(widgetGalleryDTO, profile, StringUtils.isBlank(widgetGalleryDTO.getId()));
	}

	@Override
	public WidgetGalleryDTO updateWidget(WidgetGalleryDTO widgetGalleryDTO, SpagoBIUserProfile profile) {
		if (this.canSeeGallery(profile)) {

			// Validating CODES with whitelist
			Code code = widgetGalleryDTO.getCode();
			try {
				String html = code.getHtml();

				checkXSS(html);

				String htmlCode = html;
				JSONObject jsonBody = new JSONObject(widgetGalleryDTO.getTemplate());
				JSONObject jsonCode = jsonBody.optJSONObject("code");
				jsonCode.put("html", htmlCode);
				jsonCode.put("python", widgetGalleryDTO.getCode().getPython());
				jsonCode.put("css", widgetGalleryDTO.getCode().getCss());
				jsonCode.put("javascript", widgetGalleryDTO.getCode().getJavascript());
				jsonBody.put("code", jsonCode);
				widgetGalleryDTO.setTemplate(jsonBody.toString());
			} catch (JSONException e) {
				throw new KnowageRuntimeException(e.getMessage());
			}

			SbiWidgetGallery newSbiWidgetGallery = new SbiWidgetGallery();
			newSbiWidgetGallery.getId().setUuid(widgetGalleryDTO.getId());
			newSbiWidgetGallery.setAuthor(profile.getUserId());
			newSbiWidgetGallery.setDescription(widgetGalleryDTO.getDescription());
			newSbiWidgetGallery.setName(widgetGalleryDTO.getName());
			newSbiWidgetGallery.setLabel(widgetGalleryDTO.getLabel());
			newSbiWidgetGallery.getId().setOrganization(profile.getOrganization());
			newSbiWidgetGallery.setPreviewImage(widgetGalleryDTO.getImage() != null ? widgetGalleryDTO.getImage().getBytes() : "".getBytes());
			newSbiWidgetGallery.setSbiVersionIn("");
			newSbiWidgetGallery.setTemplate(widgetGalleryDTO.getTemplate().getBytes());
			newSbiWidgetGallery.setTimeIn(Timestamp.from(Instant.now()));
			newSbiWidgetGallery.setType(widgetGalleryDTO.getType());
			newSbiWidgetGallery.setUserIn(profile.getUserId());
			newSbiWidgetGallery.setOutputType(widgetGalleryDTO.getOutputType());
			newSbiWidgetGallery.getSbiWidgetGalleryTags().clear();

			String tags = widgetGalleryDTO.getTags().toString().equals("[]") ? "" : widgetGalleryDTO.getTags().toString();
			List<SbiWidgetGalleryTag> tagList = createNewWidgetTagsByList(newSbiWidgetGallery, profile.getUserId(), tags);
			if (tagList != null) {
				newSbiWidgetGallery.getSbiWidgetGalleryTags().addAll(tagList);
			}
			sbiWidgetGalleryDao.update(newSbiWidgetGallery);
		}
		return widgetGalleryDTO;
	}

	@Override
	public int deleteGallery(String id, SpagoBIUserProfile profile) {
		if (this.canSeeGallery(profile)) {
			return sbiWidgetGalleryDao.deleteByIdTenant(id, profile.getOrganization());
		}
		return 0;
	}

	@Override
	public List<SbiWidgetGalleryTag> createNewWidgetTagsByList(SbiWidgetGallery sbiWidgetGallery, String userid, String tags) {

		List<SbiWidgetGalleryTag> tagList = null;
		if (tags.length() > 0) {
			tags = tags.substring(1, tags.length() - 1);

			String[] tagArray = tags.split(",");

			tagList = new ArrayList<>();
			ArrayList<String> insertedTags = new ArrayList<>();
			for (int i = 0; i < tagArray.length; i++) {

				tagArray[i] = tagArray[i].trim().replaceAll("\"", "");
				if (insertedTags.contains(tagArray[i])) {
					throw new KnowageRuntimeException("Cannot insert duplicate tags, please select different ones");
				}
				insertedTags.add(tagArray[i]);
				SbiWidgetGalleryTag sbiWidgetGalleryTag = new SbiWidgetGalleryTag();
				SbiWidgetGalleryTagId newId = new SbiWidgetGalleryTagId(sbiWidgetGallery.getId().getUuid(), tagArray[i],
						sbiWidgetGallery.getId().getOrganization());
				sbiWidgetGalleryTag.setId(newId);
				sbiWidgetGalleryTag.getId().setOrganization(sbiWidgetGallery.getId().getOrganization());
				sbiWidgetGalleryTag.setSbiVersionIn("");
				sbiWidgetGalleryTag.setTimeIn(Timestamp.from(Instant.now()));
				sbiWidgetGalleryTag.setUserIn(userid);
				sbiWidgetGalleryTag.setSbiWidgetGallery(sbiWidgetGallery);

				tagList.add(sbiWidgetGalleryTag);
			}
		}
		return tagList;
	}

	/*
	 * Permission methods
	 */
	@Override
	public boolean canSeeGallery(SpagoBIUserProfile userProfile) {
		boolean canSee = false;
		for (String function : userProfile.getFunctions()) {
			if (function.equalsIgnoreCase(GALLERY_FUNCTION)) {
				canSee = true;
			}
		}
		return canSee;
	}

	@Override
	public List<WidgetGalleryDTO> getWidgetsByTenantType(SpagoBIUserProfile profile, String type) throws JSONException {
		List<WidgetGalleryDTO> ret = null;
		// TODO: add a check for widget type permissions (functionality)
		ret = sbiWidgetGalleryDao.findAllByTenantAndType(profile.getOrganization(), type).stream().collect(toList());
		return ret;
	}

	@Override
	public WidgetGalleryDTO importOrUpdateWidget(WidgetGalleryDTO widgetGalleryDTO, SpagoBIUserProfile profile) throws JSONException {
		WidgetGalleryDTO existingWidget = getWidgetsById(widgetGalleryDTO.getId(), profile);
		WidgetGalleryDTO newSbiWidgetGallery = null;
		if (existingWidget == null) {
			newSbiWidgetGallery = importNewWidget(widgetGalleryDTO, profile);
		} else {
			newSbiWidgetGallery = updateWidget(widgetGalleryDTO, profile);
		}

		return newSbiWidgetGallery;
	}

	private void checkXSS(String input) {
		boolean isSafe = xssUtils.isSafe(input);

		if (!isSafe) {
			throw new InvalidHtmlPayloadException(input);
		}

	}
}
