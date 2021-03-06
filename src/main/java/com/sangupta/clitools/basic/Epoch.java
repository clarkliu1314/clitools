/**
 *
 * clitools - Simple command line tools
 * Copyright (c) 2014-2015, Sandeep Gupta
 * 
 * http://sangupta.com/projects/clitools
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.clitools.basic;

import com.sangupta.clitools.CliTool;

import io.airlift.airline.Command;

/**
 * Show the current time in epoch millis.
 * 
 * @author sangupta
 *
 */
@Command(name = "epoch", description = "Show current epoch time")
public class Epoch implements CliTool {

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}

}