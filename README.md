# A fork of Spinnery for 1.16.x
See bottom text for information about Spinnery

## Changes
- 20w16a support
- Added abillity to (manually) test Spinnery with gradle arg '-PaddTestGUIs'

## Testing
Running `./gradlew runClient -PaddTestGUIs` results in the following changes:
* Backpack item with basic container/screen usage & sync
* `/spinnery test <screen>` command for testing (client-only) screens 

# Spinnery, ahoy!

[ ![Download](https://api.bintray.com/packages/spinnery/Spinnery/spinnery/images/download.svg) ](https://bintray.com/spinnery/Spinnery/spinnery/_latestVersion)

![Spinnery](https://i.imgur.com/Mu1EqaK.png)

Spinnery is a modern, feature-complete GUI library for Minecraft 1.15 and 1.16, with a focus on making the GUI design and user experience significantly smoother and less nonsensic.

# Setup

Firstly, add Spinnery's Bintray to your `build.gradle`'s `repositories`:

```gradle
	maven {
		name = "Spinnery"
		url  "https://dl.bintray.com/spinnery/Spinnery"
	}
```

Afterwards, add it to your project:

```gradle
	// Spinnery
	modCompile "com.github.vini2003:spinnery:${project.spinnery_version}"
	include "com.github.vini2003:spinnery:${project.spinnery_version}"
```
  
 As you may realize, that's using a variable defined in `gradle.properties` for the version:
 
 ```gradle
 # Mod Properties
  spinnery_version = VERSION
```

Once that's done, you're set!
