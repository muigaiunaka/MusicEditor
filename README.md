**Project:** Music Editor

**Stack:** Java

**Team:** Muigai Unaka and Will Kent

**Summary:** This project was my first dive into Software development using the Model-View-Controller Pattern. In my Object Oriented Design course I worked on this project for 6 weeks with my partner Will Kent.


Music Editor Model
By Will Kent & Muigai Unaka

MusicEditorModel interface
METHODS:
We both had generic edit methods, we decided to ditch those and go with
specific methods to edit notes.  We also added methods to get information about
the model like getHighestBeat, getNotesStartingAt, getHighestNote and getLowestNote.

  void addNote(K note); Adds a note to the music editor’s tree map

  void removeNote(K note); Removes a note from the music editor’s tree map

  void editNote(K note); Edits the given note

  int getHighestBeat(); Finds the last beat that a note is played at

  String showMusic(); Renders a music composition to the console as a string of the note range,
  from the note with the lowest pitch and octave to the note with the highest pitch and octave

  void playSimultaneously(MusicEditorModel<K> model); Plays music notes from other compositions at
  the same time as these notes

  void playConsecutively(MusicEditorModel<K> model); Plays music notes from other compositions
  after this music model's notes

  List<K> getNotesStartingAt(int time); Gets all the notes that start at the given beat

  List<K> getAllNotes(); Gets all the notes in the music piece

  List<K> getNotesAtTime(int time); Gets the list of notes that are played at the given beat

  K getLowestNote(); Gets the lowest note in the piece of music

  K getHighestNote(); gets the highest note in the piece of music

  List<Integer> getBeatsFor(K note); gets all of the beats that a given note is played during

  int getLength(); gets the length of the piece in beats

  int getTempo(); gets the tempo of the piece

  List<?> getPitches(); gets the range of pitches in the piece

MusicEditorModelImpl:
MusicEditorModel is implemented by MusicEditorModelImpl.
We decided to use a tree map for the notes.  This allows the notes to be fetched by beat in
constant time.  The model keeps track of the length of the piece and the highest and lowest
beats as well as a list of pitches.  These fields help to keep track of the bounds of the model
as well as the range of notes by pitch and by beat.
We added a list<Pitch> field to keep track of all of the pitches in the range of pitches.
We also added methods to expand the length (beats) and range (pitches) of the music piece as
notes are added.  The addNote() method is significantly more robust than before.

FIELDS:
	int length: the number of beats in the music piece

	pitch lowest: the pitch of the lowest note in the piece

	pitch highest: the pitch of the highest note in the piece

	list<Pitch> pitches: a list of pitches from lowest to highest

	TreeMap<Integer, List<Note>> song: a map of all of the notes in the piece by beat
	int tempo: the speed of the piece

METHODS:
   All methods from the interface plus:
   	private void setLength(int newLength); Makes the piece of music longer if notes
   	are added that go beyond the current length
	private void setLowest(Note note); Extends the range of the piece downward if
	a note is added below the current lowest note.
	private void setHighest(Note note); Extends the range of the piece upward if a
	note is added above the current highest note.

	public boolean containsNote(Note note); Determines if this music piece contains the given note

Private Class Builder
	This class also contains a private static Builder class that sets the tempo and can add
	notes and then constructs new MusicEditorModelImpls from that information.  The other fields
	in the model are set based on the notes in the model (highest and lowest are the highest and
	lowest notes in the list, length is the latest beat at which a note is being played and pitches
	is a list of pitches from the pitch of the lowest note to the pitch of the highest note).


Note:
Note implements comparable
	We decided not to include an end time for notes because it can be easily calculated from
	the start time and the duration.  Having and end time as well as those two made keeping
	track of the fields complicated because if one changed the others may also have to change
	to keep the note consistent.
FIELDS:
	Pitch pitch: a pitch for the note

	int startTime: the beat the note starts on

	int duration: the number of beats the note plays for

	int instrument: the instrument the note is played with

	int volume: the volume of the note
METHODS:
   CompareTo() the interface plus:

	void higher(); increases the pitch of this note by one

	void lower(); decreases the pitch of this note by one

	int getDuration(); getter method of the duration of this note

  	int getInstrument(); getter method for the instrument of this method

  	Pitch getPitch(); getter method for the pitch of this note

  	int getStartTime(); getter method for the start beat of this note

  	void setDuration(int duration); setter method for the duration of a note

  	void setInstrument(int instrument); setter method for the instrument of a note

  	void setPitch(Pitch pitch); Setter method for the Pitch of a note

	void setStartTime(int startTime); Setter method for the start time of a note

	int getVolume(); gets the volume of this note




Pitch:
Pitch implements comparable
Pitch is a combination of an enum Tone which is C through GSHARP and an octave. There are two
constructors for a pitch. One takes a Tone and an int octave and constructs the note from that.
The other takes an int and then calculates the tone and octave values from it using math.

FIELDS:
	Tone t; the tone of the pitch
	int octave; the octave of the pitch

METHODS:
   Overridden toString(), equals(), hashCode() and compareTo()

  public void higher();increases the pitch by one letter, when going from B to C it also
  increase the octave

  public void lower(); decreases the pitch by one letter, when going from C to B it also
  decreases the octave

  public int getInt(); get an integer value of the pitch

  public Tone getTone(); get the tone of the pitch


Tone:
Tone is an enumeration for each of the letters that represent pitches without octaves.

METHODS:
   Overridden toString

  int getToneValue(); get the ordinal value of the tone

  String getToneLetter(int pitch); get the letter value of the tone from the given pitch

  Tone getNext(); returns the next letter in the enum and wraps if at the end

  Tone getPrev();returns the previous letter in the enum and wraps if at the beginning


View interface:
Most of the methods in the view interface are there to show the user where they are in the edit
process.  The graphical views don’t do anything with these methods since they show all of the notes
at once but the Midi view tracks through the beats and can play them individually or pause at
a certain beat.
METHODS:

    void initialize(); Sets up the view with any default parameters and displays any relevant
    information

    void play(); Plays the song

    void pause(); Pauses the song at the current beat

    void rewind(); Moves backwards through the song

    void skip(); Skips to the end of the song

    void next(); Moves to the next beat























