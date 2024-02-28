<%--
Knowage, Open Source Business Intelligence suite
Copyright (C) 2016 Engineering Ingegneria Informatica S.p.A.

Knowage is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

Knowage is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>

<%
String code = request.getParameter("code");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>OAuth2 standard authorization code flow</title>
  </head>
  <body>
    <script>
    var oauth2Config = null;
	
    var xhrOAuth2C = new XMLHttpRequest();

    xhrOAuth2C.onload = function() {
        var response = xhrOAuth2C.response;

        if (xhrOAuth2C.status == 200) {
        	oauthConfig = response;
        } else {
            alert("Error: " + response.error_description + " (" + response.error + ")");
        }
    };
    xhrOAuth2C.responseType = 'json';
    xhrOAuth2C.open("GET", '/oauth2configservice', true);
    xhrOAuth2C.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhrOAuth2C.send();

    const authorizeEndpoint = oauth2Config.authorizeUrl;
    const tokenEndpoint = oauth2Config.accessTokenUrl;
    const clientId = oauth2Config.clientId;
    const redirectUri = oauth2Config.redirectUrl;
    const scope = oauth2Config.scopes;
    const code = <%= code  %>;
    const accessTokenResponse;
    if(code == null){
    	accessTokenResponse = null;
    } else {
        var xhrO2AT = new XMLHttpRequest();

        xhrO2AT.onload = function() {
            var response = xhrO2AT.response;

            if (xhr.status == 200) {
            	accessTokenResponse = response;
            } else {
                alert("Error: " + response.error_description + " (" + response.error + ")");
            }
        };
        xhrO2AT.responseType = 'json';
        xhrO2AT.open("GET", '/oauth2clientservice', true);
        //xhrO2AT.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhrO2AT.send(new URLSearchParams({code: code});   	
    }

        if (window.location.search) {
            var args = new URLSearchParams(window.location.search);
            var state = args.get("state");
            
            if (accessTokenResponse) {
            	if (window.sessionStorage.getItem("state") !== state){
            	    throw Error("Probable session hijacking attack!");
            	}

        		var access_token = accessTokenResponse.accessToken;
        		var id_token = accessTokenResponse.idToken;
        		if (id_token) {
        			// storing id_token for later usage (on logout)
                	window.sessionStorage.setItem("id_token", id_token);
        		}
        		
        		var lastRedirectUri = window.location.href.split('?')[0];
        		var extraParameters = popSavedExtraQueryParameters();
        		
            	var args = new URLSearchParams({
            		PAGE : "LoginPage",
            		NEW_SESSION : "TRUE",
                    access_token: access_token
                });
                window.location = lastRedirectUri + "?" + args + "&" + extraParameters;

            } else {
            	saveExtraQueryParameters();
            	startOauth2Flow();
            }
        }

        function startOauth2Flow() {
        	var state = generateRandomString(64);

           	window.sessionStorage.setItem("state", state);

            var args = new URLSearchParams({
                response_type: "code",
                client_id: clientId,
                state: state,
                redirect_uri: redirectUri,
                scope: scope
            });
            window.location = authorizeEndpoint + "?" + args;
        }

        function generateRandomString(length) {
            var text = "";
            var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

            for (var i = 0; i < length; i++) {
                text += possible.charAt(Math.floor(Math.random() * possible.length));
            }

            return text;
        }
        
        function saveExtraQueryParameters() {
        	var args = new URLSearchParams();
        	if (window.location.search) {
                args = new URLSearchParams(window.location.search);
                // removing unnecessary parameters
                args.delete('PAGE');
                args.delete('NEW_SESSION');
                args.delete('ACTION_NAME');
        	}
        	// storing extra-query-parameters for later usage (when user is redirected into knowage)
        	window.sessionStorage.setItem("extra-query-parameters", args);
        }
        
        function getSavedExtraQueryParameters() {
        	return window.sessionStorage.getItem("extra-query-parameters");
        }
        
        function removeSavedExtraQueryParameters() {
        	window.sessionStorage.removeItem("extra-query-parameters");
        }
        
        function popSavedExtraQueryParameters() {
        	var toReturn = getSavedExtraQueryParameters();
        	removeSavedExtraQueryParameters();
        	return toReturn;
        }
        
    </script>
  </body>
</html>