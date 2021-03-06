/*
 * Copyright 2012 jMethods, Inc. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myjavaworld.gui;

import java.awt.Font;

import javax.swing.plaf.FontUIResource;

/**
 * Default - Large theme for displaying the user interface elements enlarged.
 * 
 * @author Sai Pullabhotla, psai [at] jMethods [dot] com
 * @version 2.0
 * 
 */
public class DefaultLargeTheme extends DefaultTheme {

	private static final FontUIResource controlFont = new FontUIResource(
			"Dialog", Font.BOLD, 16);
	private static final FontUIResource systemFont = new FontUIResource(
			"Dialog", Font.BOLD, 16);
	private static final FontUIResource userFont = new FontUIResource("Dialog",
			Font.BOLD, 16);
	private static final FontUIResource smallFont = new FontUIResource(
			"Dialog", Font.BOLD, 12);

	@Override
	public String getName() {
		return "Default Theme - Large";
	}

	@Override
	public FontUIResource getControlTextFont() {
		return controlFont;
	}

	@Override
	public FontUIResource getSystemTextFont() {
		return systemFont;
	}

	@Override
	public FontUIResource getUserTextFont() {
		return userFont;
	}

	@Override
	public FontUIResource getMenuTextFont() {
		return controlFont;
	}

	@Override
	public FontUIResource getWindowTitleFont() {
		return controlFont;
	}

	@Override
	public FontUIResource getSubTextFont() {
		return smallFont;
	}
}
