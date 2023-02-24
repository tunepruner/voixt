package com.tunepruner.voixt.editor.editorscreen.datasource

/** This object is in charge of keeping time from when a
 * user starts recording. */
object TimeDataSource {
    private var startedRecordingTimeStamp: Long = 0L

    fun startRecording() { startedRecordingTimeStamp = System.nanoTime() }
    fun getCurrentTime() = (System.nanoTime() - startedRecordingTimeStamp)
}