package org.python.pydev.django_template_editor.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.python.pydev.django_template_editor.DjangoPlugin;

/**
 * @author Zbigniew Kacprzak
*/
public class DjangoOutlinePreferencesPage extends FieldEditorPreferencePage {

	private IPreferenceStore  		mStore;
	
	DjangoOutlinePreferencesPage() {
		super("Outline preferences", FLAT);
		//mStore = DjangoPlugin.getDefault().getPreferenceStore();
        //setPreferenceStore(mStore);		
	}
	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
	}
}
