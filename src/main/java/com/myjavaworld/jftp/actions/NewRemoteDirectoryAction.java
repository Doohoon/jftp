/**
 *
 * This software is the confidential and proprietary information of the author,
 * Sai Pullabhotla. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement you
 * entered into with the author.
 *
 * THE AUTHOR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. THE AUTHOR SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 */
package com.myjavaworld.jftp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.myjavaworld.jftp.FTPSession;
import com.myjavaworld.jftp.JFTP;
import com.myjavaworld.jftp.NewRemoteDirectoryDlg;

/**
 * An implementation of Action interface used by the New Remote Directory menu
 * tem and tool bar button.
 * 
 * @author Sai Pullabhotla, psai [at] jMethods [dot] com
 * @version 2.0
 * 
 */
public class NewRemoteDirectoryAction implements ActionListener {

	private JFTP jftp = null;
	private static NewRemoteDirectoryAction instance = null;

	private NewRemoteDirectoryAction(JFTP jftp) {
		this.jftp = jftp;
	}

	public static synchronized NewRemoteDirectoryAction getInstance(JFTP jftp) {
		if (instance == null) {
			if (instance == null) {
				instance = new NewRemoteDirectoryAction(jftp);
			}
		}
		return instance;
	}

	public void actionPerformed(ActionEvent evt) {
		FTPSession session = jftp.getCurrentSession();
		if (session == null || !session.isConnected()) {
			return;
		}
		NewRemoteDirectoryDlg dlg = new NewRemoteDirectoryDlg(jftp);
		dlg.setLocationRelativeTo(jftp);
		dlg.setVisible(true);
		String directory = dlg.getDirectory();
		dlg.dispose();
		if (directory != null) {
			session.createRemoteDirectory(directory);
		}
	}
}