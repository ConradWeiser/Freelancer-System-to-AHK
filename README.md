# Event Solar Spawning Assistance
Written for the [Discovery Gaming Community](http://www.discoverygc.com/forums), this program was designed in order to take specific objects from Freelancer system .ini files, and be able to convert specific objects into the ingame command syntax to actually spawn these objects.

This allows a user to design solar placement graphically, with a [system editor](http://stfx.github.io/freelancermodstudio/), and be able to automatically grab ONLY the new objects for spawning.

The output is formatted as an [AutoHotkey](https://autohotkey.com/) script, allowing the chain of commands for each object to be automatically typed into the game client, with a single key press.

## Details
The tool checks for all of the objects in a system, and allows you to filter the nickname field of each object, by prefix. 

Thus if you give all of the new objects you're looking to create a macro to spawn ingame, all you need to do is give each object a common prefix. (In the example image, the prefix is 'event_')

![Editor Image](https://image.ibb.co/giQoOS/Editor.png)

Afterwards, simply boot up the little tool, load the INI file, and select the prefix you'd like to use

![Pre-prefix](https://image.ibb.co/j4f53S/loadIni.png)
![Post-prefix](https://image.ibb.co/dQw1cn/filterini.png)

Save the macro, and it'll spit out an AHK script wherever you choose it to be placed called buildMacro.ahk - Load this up, and you're all set. Commands to trigger the print scripts are listed below!

The script generated looks something like..
```^+v::
SEND, {ENTER}
SEND, .custombasecreate space_beamx_dmg null_loadout Solar -69106.0 0.0 -2754.0 0 0 0 fc_neutral event_thing1
SEND, {ENTER}
SEND, {ENTER}
SEND, .custombasecreate space_beamx_dmg null_loadout Solar -68778.0 0.0 -4189.0 0 0 0 fc_neutral event_thing2
SEND, {ENTER}
SEND, {ENTER}
SEND, .custombasecreate space_beamx_dmg null_loadout Solar -68778.0 0.0 -4189.0 0 0 0 fc_neutral event_thing3
SEND, {ENTER}
SEND, {ENTER}
SEND, .custombasecreate space_beamx_dmg null_loadout Solar -69352.0 0.0 -4189.0 0 0 0 fc_neutral event_thing4
SEND, {ENTER}
SEND, {ENTER}
SEND, .custombasecreate space_beamx_dmg null_loadout Solar -68778.0 0.0 -769.0 0 0 0 fc_neutral event_thing5
SEND, {ENTER}


^+z::
SEND, {ENTER}
SEND, .basedestroy event_thing1
SEND, {ENTER}
SEND, {ENTER}
SEND, .basedestroy event_thing2
SEND, {ENTER}
SEND, {ENTER}
SEND, .basedestroy event_thing3
SEND, {ENTER}
SEND, {ENTER}
SEND, .basedestroy event_thing4
SEND, {ENTER}
SEND, {ENTER}
SEND, .basedestroy event_thing5
SEND, {ENTER}
```

Messy? Maybe. But it's the correct syntax to get the job done.


## Usage

- Make sure that AutoHotkey is installed, without the script driver, the output files will be useless
- Create your system. If from scratch, you don't need to worry about prefixes. If you're editing an existing system, make sure that you prefix new objects, so the script doesn't spawn in already existing objects where they're placed currently.
- Load up the tool, and load the ini file by clicking the proper button
- Add the prefix into the text area.
- Save the macro
- Run the macro, and reference the commands for usage

## Commands
Control + Shift + V -- Spawn all of the objects

Control + Shift + Z -- Delete all of the objects (Loud! Turn down your volume)

## Things to note

-   If you do not insert any rotation values in the editor, they will default to 0, 0, 0  
-   If you do not insert a loadout for the object, it will default to null_loadout  
-   If you do not insert an object reputation, it will default to fc_neutral  
-   Parameter 3 of the command is 'type' - This is PERMANENTLY set to Solar. I do not know the other options. With some refresher (or a pointer to the FLHook plugin) I'll free that up for customization.  
-   To name the objects appropriately, I recommend you give them in the editor, an empty infocard value, and override it serverside with the correct name if it's targetable.

