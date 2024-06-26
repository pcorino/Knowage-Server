/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2016 Engineering Ingegneria Informatica S.p.A.
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
package it.eng.spagobi.engines.commonj.process;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class CmdExecWork extends SpagoBIWork {

	private static final Logger LOGGER = Logger.getLogger(CmdExecWork.class);

	String command;
	String commandEnvironment;

	/** parameters  passed to command*/
	List<String> cmdParameters;
	/** to be added to classpath*/
	List<String> classpathParameters;
	/** Logger */
	/** the process aunche*/
	Process process = null;
	/** the flag for automatic instance ID (external creation) */
	static final String INSTANCE_AUTO="INSTANCE=AUTO";

	@Override
	public boolean isDaemon() {
		return false;
	}

	@Override
	public void release() {
		LOGGER.debug("IN");
		super.release();
		if(process != null){
			process.destroy();
			LOGGER.info("Release the JOB");
		}
		LOGGER.debug("OUT");
	}




	/** this method executes command followed by command parameters taken from template
	 *  and by sbi parameters
	 *  and add classpath variables followed by -cp
	 *
	 * @param cmd
	 * @param envFile
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public int execCommand() throws IOException {
		LOGGER.debug("IN");
		File directoryExec = null;
		boolean isInstanceAuto = false;

		if(commandEnvironment != null) {
			directoryExec = new File(commandEnvironment);
			LOGGER.info("commandEnvironment=" + commandEnvironment);
		}
		// add -cp
		if (!classpathParameters.isEmpty()) {
			command += " -cp ";
			for (Iterator iterator = classpathParameters.iterator(); iterator.hasNext();) {
				String add = (String) iterator.next();
				command += add;
				if(iterator.hasNext()){
					command += ";";
				}
			}
		}

		command += " ";

		// add command parameters
		for (Iterator iterator = cmdParameters.iterator(); iterator.hasNext();) {
			String par = (String) iterator.next();
			command += par + " ";
			isInstanceAuto = (par.toUpperCase().indexOf(INSTANCE_AUTO) > -1);
		}


		// select sbi driver from MAP and add values!
		for (Iterator iterator = analyticalParameters.iterator(); iterator.hasNext();) {
			String url= (String) iterator.next();
			if (sbiParameters.get(url) != null){
				Object value = sbiParameters.get(url);
				if (value!=null && !value.equals("") ) {
					command += url + "=" + value.toString() +" ";
				}
			}
		}

		// add pid to command if it's non defined as 'AUTO' definition
		if (!isInstanceAuto){
			String pidStr = "instance="+pid;
			command += pidStr;
		}


    	if(isRunning()){
			LOGGER.info("launch command " + command);
			process = Runtime.getRuntime().exec(command, null, directoryExec);
			LOGGER.info("Wait for the end of the process... ");

			StreamGobbler errorGobbler = new
			StreamGobbler(process.getErrorStream(), "ERROR");

			StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "OUTPUT");

			errorGobbler.start();
			outputGobbler.start();

			LOGGER.info("Process END " + command);
		}
		else{
			LOGGER.warn("Command not launched cause work has been stopper");
		}

		LOGGER.debug("OUT");
		return 0;

	}


	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommandEnvironment() {
		return commandEnvironment;
	}

	public void setCommandEnvironment(String commandEnvironment) {
		this.commandEnvironment = commandEnvironment;
	}

	public List<String> getCmdParameters() {
		return cmdParameters;
	}

	public void setCmdParameters(List<String> cmdParameters) {
		this.cmdParameters = cmdParameters;
	}

	public List<String> getClasspathParameters() {
		return classpathParameters;
	}

	public void setClasspathParameters(List<String> classpathParameters) {
		this.classpathParameters = classpathParameters;
	}


	class StreamGobbler extends Thread {
		InputStream is;
		String type;

		StreamGobbler(InputStream is, String type) {
			this.is = is;
			this.type = type;
		}

		@Override
		public void run() {
			try {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null)
					System.out.println(type + ">" + line);
			} catch (IOException ioe) {
				LOGGER.error(ioe);
			}
		}
	}

}
