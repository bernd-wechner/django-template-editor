/**
 *
 */
package org.python.pydev.django_template_editor.templates;

import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.texteditor.templates.TemplatePreferencePage;
import org.python.pydev.django_template_editor.DjangoPlugin;

/**
 * @author Zbigniew Kacprzak
 */
public class DjangoTemplatesPreferencesPage extends TemplatePreferencePage implements IWorkbenchPreferencePage {

	public DjangoTemplatesPreferencesPage() {
		setDescription("Templates for the Django Template Editor");
		setPreferenceStore(DjangoPlugin.getDefault().getPreferenceStore());
		setTemplateStore(TemplateManager.getDjangoTemplateStore());
		setContextTypeRegistry(TemplateManager.getDjangoContextTypeRegistry());
	}

    protected boolean isShowFormatterSetting() {
        return false;
    }

    @SuppressWarnings("deprecation")
	public boolean performOk() {
        boolean ok = super.performOk();
        DjangoPlugin.getDefault().savePluginPreferences();
        return ok;
    }

}
