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

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 * An extension of <code>javax.swing.MTextField</code>.
 * 
 * @author Sai Pullabhotla, psai [at] jMethods [dot] com
 * @version 1.0
 * 
 */
public class MLabelTextField extends JTextField implements MTextComponent,
		MouseListener {

	private UndoManager undoManager = null;
	private boolean first = true;

	/**
	 * Creates an instance of <code>MTextField</code>.
	 * 
	 */
	public MLabelTextField() {
		super();
		setup();
	}

	/**
	 * Creates an instance of <code>MTextField</code>.
	 * 
	 * @param columns
	 *            Display size
	 * 
	 */
	public MLabelTextField(int columns) {
		super(columns);
		setup();
	}

	@Override
	public void setText(String text) {
		super.setText(text);
		setCaretPosition(0);
		if (first) {
			first = false;
			getDocument().addUndoableEditListener(undoManager);
		}
	}

	@Override
	public void setDocument(Document model) {
		model.addUndoableEditListener(undoManager);
		super.setDocument(model);
	}

	public void setMaximumLength(int maxLength) {
		SingleLineDocument model = (SingleLineDocument) getDocument();
		model.setMaximumLength(maxLength);
	}

	public int getMaximumLength() {
		SingleLineDocument model = (SingleLineDocument) getDocument();
		return model.getMaximumLength();
	}

	public void setCharacterCase(int characterCase) {
		SingleLineDocument model = (SingleLineDocument) getDocument();
		model.setCharacterCase(characterCase);
	}

	public int getCharacterCase() {
		SingleLineDocument model = (SingleLineDocument) getDocument();
		return model.getCharacterCase();
	}

	public void setUndoLimit(int undoLimit) {
		undoManager.setLimit(undoLimit);
	}

	public int getUndoLimit() {
		return undoManager.getLimit();
	}

	public void delete() {
		Document model = getDocument();
		int selectionStart = getSelectionStart();
		int selectionEnd = getSelectionEnd();
		if (!(selectionStart < 0 && selectionEnd < 0)) {
			try {
				model.remove(selectionStart, selectionEnd - selectionStart);
			} catch (BadLocationException exp) {
				System.err.println(exp);
				exp.printStackTrace();
			}
		}
	}

	public void undo() throws CannotUndoException {
		undoManager.undo();
	}

	public void redo() throws CannotRedoException {
		undoManager.redo();
	}

	public boolean canCut() {
		if (!isEditable()) {
			return false;
		}
		return getSelectionEnd() - getSelectionStart() > 0;
	}

	public boolean canCopy() {
		return getSelectionEnd() - getSelectionStart() > 0;
	}

	public boolean canPaste() {
		if (!isEditable()) {
			return false;
		}
		Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = clipBoard.getContents(this);
		return t.isDataFlavorSupported(DataFlavor.stringFlavor);
	}

	public boolean canDelete() {
		if (!isEditable()) {
			return false;
		}
		return getSelectionEnd() - getSelectionStart() > 0;
	}

	public boolean canSelectAll() {
		return true;
	}

	public boolean canUndo() {
		if (!isEditable()) {
			return false;
		}
		return undoManager.canUndo();
	}

	public boolean canRedo() {
		if (!isEditable()) {
			return false;
		}
		return undoManager.canRedo();
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	public void mousePressed(MouseEvent evt) {
		requestFocusInWindow();
		if (evt.isPopupTrigger()) {
			EditPopupMenu.getInstance().show(this, evt.getX(), evt.getY());
		}
	}

	public void mouseReleased(MouseEvent evt) {
		if (evt.isPopupTrigger()) {
			EditPopupMenu.getInstance().show(this, evt.getX(), evt.getY());
		}
	}

	public void mouseClicked(MouseEvent evt) {
		// if (SwingUtilities.isRightMouseButton(evt)) {
		// EditPopupMenu.getInstance().show(this, evt.getX(), evt.getY());
		// }
	}

	@Override
	protected Document createDefaultModel() {
		SingleLineDocument model = new SingleLineDocument();
		return model;
	}

	private void setup() {
		undoManager = new UndoManager();
		undoManager.setLimit(1);
		addMouseListener(this);
		setAppearance();
	}

	private void setAppearance() {
		setOpaque(false);
		setEditable(false);
		setBorder(BorderFactory.createEmptyBorder());
	}
}