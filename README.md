# Django Template Editor for the Eclipse IDE



Eclipse Preparations
--------------------

Install the Plug-in Development tools.

In Eclipse:

- Help > Install New Software...
- Work With: --All Available Sites---
- Filter: Eclipse Plug-In
- Select: Eclipse Plug-in Development Environment
- Install

You've now installed PDE (the Plug-in Development Environment)

Prepare the source
------------------
Install the source in a folder in your Eclipse workspace. We will call it "django-editor"
In the subfolder: org.python.pydev.django_template_editor.updates
    delete artifacts.jar and contents.jar

Eclipse project
----------------
In Eclipse, add the django-editor folder as a project.
It has three subfolders:
    org.python.pydev.django_template_editor.feature
    org.python.pydev.django_template_editor.updates
    org.python.pydev.django_template_editor.plugin

Build
-----
1. Open org.python.pydev.django_template_editor.plugin/plugin.xml
    - You should see an editor open (supplied by PDE) which has a selection of links on the right
    - At bottom right should see an Export Wizard link
    - Click that
    - Destination: org.python.pydev.django_template_editor.updates
2. Open org.python.pydev.django_template_editor.feature/feature.xml
    - You should see an editor open (supplied by PDE) which has a selection of links on the right
    - At bottom right should see an Export Wizard link
    - Click that
    - Destination: org.python.pydev.django_template_editor.updates
3. Open org.python.pydev.django_template_editor.updates/site.xml
    - You should an Update Site Map editor open
    - On the center bar there is a Build All button
    - Click Build All

Install
-------
Install the django editor from the updates folder:

In Eclipse:
    Help > Install New Software...
    Click Manage
    Click Add
    Name: Django Editor
    Location: the org.python.pydev.django_template_editor.updates that you built above
    Work With: Django Editor
    Select Django Templates Editor
    Click Finish

## An Eclipse "Software" Primer

I wouldn't be the first or only one to find Eclipse's terminology and structure more than a little arcane and nebulous.  To with a really quick primer on some of the terms and context:

- Eclipse itself:
  - is in fact a software platform written in Java and for Java, upon which you can build software products.
  - provides a Rich Client Platform (RCP) for developing desktop applications
  - provides the Eclipse IDE (Integrated Development Environment) which is an desktop application built on the Eclipse RCP.
  - ref: https://www.vogella.com/tutorials/EclipseRCP/article.html
- Eclipse supports a rich plugin architecture and this editor is such a plugin ... sort of. There are some words you'll see around that confuse the matter:
  - It's actually "software". That's what it's called in the Eclipse IDE.  It can be installed it with **Help > Install New Software...** menu.
  - It's installed from a "site". That's what you define on the **Install (Available Software)** dialog box where we are asked to **Work with:**  an *Available site*.
  - The site is also called a "repository". If you want to add a new "site" from which to install "software" you click **Add** on the **Install (Available Software)** dialog box which displays the **Add Repository** dialog box.
    - It's also called an "Update site": [Eclipse Help: Update Site]([https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.pde.doc.user%2Fconcepts%2Fupdate_site.htm&cp%3D4_1_6) 
  - To create a "site"/"repository", that Eclipse can install "software" from you need a "feature". A "feature" is just a plugin packaging device. It defines what plugins it includes, in the case of one plugin alone, that one. From the "feature" a "site"/"repository" can be built.
  - A "plugin" is the actual software that is installed and can add to the elements Eclipse IDE. This Django Template Editor is, at its heart, a plugin, which is wrapped in a "feature" to create "site"/"repository" so you can install it.
  - a "fragment" is software the extends an existing "plugin". You write a "fragment" that can be installed if you wanted to extend another "plugin" you don't have access to or control over.
  - And then there are "artifacts"
- Specific to this plugin, the Django Template Editor:
  - There is a "plugin": 

## Uninstalling and Reinstalling Cleanly

Eclipse can be a slight bother when testing installation round tripping. In the ideal, we could make a change to the plugin and test the site creation, the install, and functionality without and dramas.  

To uninstall the Django Template Editor, in Eclipse ID

- Click **About Eclipse IDE** on the **Help** menu.
- Click **Installation Details** on on the **About Eclipse IDE** dialog box
- Type **django** in the **type filter text** box
- Click on **Django Template Editor** to select it
- Click **Uninstall...**
- This will insist on restarting Eclipse alas.

The problem is if you try to install the plugin again, unless the version has incremented Eclipse will not install it, because it never really uninstalled it and only disabled it, it lied to us! And I personally detest incrementing it during upskilling, testing, trying things out or just as bad the habit in the Eclipse world of adding a .qualifier that encodes the date-time, so that you can do such tests without incrementing an actually relevant part of the version number).

To wit, through trial and error I can report that a clean uninstall requires the following followup after uninstalling it as above:

- Find where the eclipse instance is that you run. 
  There's a executable, called `eclipse` or `eclipse.exe` (if you're one of *those* systems) which you run to start Eclipse. 
  You either know where that is or could find it by various methods:

  - You could try `which eclipse` or something like that from a command line
  - You could find it on a system menu, right-click and see if `properties` tells you what that system menu item starts
  - You could start and look for it in a task explorer of your choice and see what it says
  - In Eclipse IDE itself you could click **About Eclipse** on the **Help** menu, then **Installation Details** then the **Configuration** tab, and on that look for `eclipse.home.location`
  - We'll call that *$eclipse_home* from here on in for convenience

- Now there are three places to clean up (when Eclipse is *not* running):

  - Delete the feature directory: `$eclipse_home/features/org.python.pydev.django_template_editor*`

  - Delete the plugin directory: `$eclipse_home/plugins/org.python.pydev.django_template_editor*`

  - Remove all references from the artifacts register: 

    - Remove all references to `org.python.pydev.django_template_editor` in `$eclipse_home/artifacts.xml`

    - There will one or entries like this (for the plugin and/or feature):

      ```xml
          <artifact classifier='osgi.bundle' id='org.python.pydev.django_template_editor.plugin' version='1.0.0'>
            <properties size='1'>
              <property name='download.size' value='148699'/>
            </properties>
            <repositoryProperties size='1'>
              <property name='artifact.folder' value='true'/>
            </repositoryProperties>
          </artifact>
      ```

- Then start Eclipse and try installing it again, and fingers crossed, works for me, and I can reinstall.

## Using Dropins Instead

For testing and round tripping it's much easier in fact to use Eclipses dropins feature.

To use it you'll need to know eclipses home directory, the directory where the eclpse instance is that you run.  See the first step under Uninstalling and Reinstalling Cleanly above for that.

In that folder you'll see a folder called `dropins`. One of the beauties of using dropins is we don't need the feature at all, we can publish just the plugin (the feature is just a packaging mechanism for creating the update site from whence the plugin can be installed)

To publish it you need only copy the `plugins` folder to `$eclipse_home/dropins/<whatever name you like but django-template-editor is a good choice>/plugins`

To do that I've included a bash script `dropin`
