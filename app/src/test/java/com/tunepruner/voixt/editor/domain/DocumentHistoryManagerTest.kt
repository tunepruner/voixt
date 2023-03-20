package com.tunepruner.voixt.editor.domain

import android.text.SpannableStringBuilder
import com.tunepruner.voixt.editor.editorscreen.domain.DUMMY_TEXT
import com.tunepruner.voixt.editor.editorscreen.domain.DocumentHistoryManager
import com.tunepruner.voixt.editor.editorscreen.model.toDomainString
import com.tunepruner.voixt.editor.util.concatenateToOneString
import kotlinx.coroutines.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class DocumentHistoryManagerTest {
    private val _sut = DocumentHistoryManager()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `example of how to reassign test scheduler`() = runTest {
        val scheduler = testScheduler
        val dispatcher = StandardTestDispatcher(scheduler)
        launch(dispatcher) {
            // Possibly use later when needing to test multi-thread operations
        }
    }

    @Test
    fun `add word and test against backup`() = runTest {
        _sut.initialize(DUMMY_TEXT.split(' ').map { it.toDomainString() })
        _sut.add(listOf("ADDED", "WORDS").map { it.toDomainString() }, 5)
        println(_sut.compileListFromEditStack().map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        assert(_sut.compileListFromEditStack().size == _sut.backupTextBodyState.value.size)
    }

    @Test
    fun `add words and test size of list`() = runTest {
        _sut.initialize(DUMMY_TEXT.split(' ').map { it.toDomainString() })
        val compiledListFromEditStack = _sut.compileListFromEditStack()
        val initialSize = compiledListFromEditStack.size
        val wordsToAdd = listOf("ADDED", "WORDS")
        _sut.add(wordsToAdd.map { it.toDomainString() }, 5)
        val newSize = _sut.compileListFromEditStack().size
        assert(newSize == initialSize + wordsToAdd.size)
    }

    @Test
    fun `remove words and test against backup`() = runTest {
        _sut.initialize(DUMMY_TEXT.split(' ').map { it.toDomainString() })
        val wordsToRemove = _sut.backupTextBodyState.value.subList(5, 7)
        _sut.remove(5, wordsToRemove)
        println(_sut.compileListFromEditStack().map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(wordsToRemove.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        assert(_sut.compileListFromEditStack().size == _sut.backupTextBodyState.value.size)
    }

    @Test
    fun `remove words and test size of list`() = runTest {
        _sut.initialize(DUMMY_TEXT.split(' ').map { it.toDomainString() })
        val initialSize = _sut.compileListFromEditStack().size
        val wordsToRemove = _sut.backupTextBodyState.value.subList(5, 7)
        _sut.remove(5, wordsToRemove)
        assert(_sut.compileListFromEditStack().size == initialSize - wordsToRemove.size)
    }

    @Test
    fun `add word, then undo, and test against backup`() = runTest {
        _sut.initialize(DUMMY_TEXT.split(' ').map { it.toDomainString() })
        _sut.add(listOf("ADDED", "WORDS").map { it.toDomainString() }, 5)
        _sut.undo()
        assert(_sut.compileListFromEditStack().size == _sut.backupTextBodyState.value.size)
    }

    @Test
    fun `add word, remove word, then undo, and test against backup`() = runTest {
        _sut.initialize(DUMMY_TEXT.split(' ').map { it.toDomainString() })
        _sut.add(listOf("ADDED", "WORDS").map { it.toDomainString() }, 5)
        _sut.remove(5, listOf("ADDED", "WORDS").map { it.toDomainString() })
        _sut.undo()
        assert(_sut.compileListFromEditStack().size == _sut.backupTextBodyState.value.size)
    }

    @Test
    fun `add word, remove word, then undo, then redo, and test against backup`() = runTest {
        _sut.initialize(DUMMY_TEXT.split(' ').map { it.toDomainString() })
        _sut.add(listOf("ADDED", "WORDS").map { it.toDomainString() }, 5)
        _sut.remove(5, listOf("ADDED", "WORDS").map { it.toDomainString() })
        _sut.undo()
        _sut.redo()
        assert(_sut.compileListFromEditStack().size == _sut.backupTextBodyState.value.size)
    }

    @Test
    fun `undo on empty document does not crash`() = runTest {
        _sut.initialize(ArrayList<String>().map { it.toDomainString() })
        _sut.undo()
        assert(_sut.compileListFromEditStack().size == _sut.backupTextBodyState.value.size)
    }

    @Test
    fun `expected list sizes after undo once`() = runTest {
        _sut.initialize(arrayListOf())
        _sut.add(arrayListOf("One").map { it.toDomainString() })
        _sut.add(arrayListOf("Two").map { it.toDomainString() })
        _sut.add(arrayListOf("Three").map { it.toDomainString() })
        _sut.undo()
        assert(_sut.backupTextBodyState.value.size == 2)
        assert(_sut.compileListFromEditStack().size == 2)
    }

    @Test
    fun `expected list sizes after two undos and one redo`() = runTest {
        val backup = _sut.backupTextBodyState
        _sut.initialize(arrayListOf())
        _sut.add(arrayListOf("One").map { it.toDomainString() })
        _sut.add(arrayListOf("Two").map { it.toDomainString() })
        _sut.add(arrayListOf("Three").map { it.toDomainString() })
        _sut.undo()
        _sut.undo()
        _sut.redo()
        assert(_sut.backupTextBodyState.value.size == 2)
        assert(_sut.compileListFromEditStack().size == 2)
        println(backup)
    }

    @Test
    fun `expected list sizes after three undos`() = runTest {
        val backup = _sut.backupTextBodyState
        _sut.initialize(arrayListOf())
        _sut.add(arrayListOf("One").map { it.toDomainString() })
        _sut.add(arrayListOf("Two").map { it.toDomainString() })
        _sut.add(arrayListOf("Three").map { it.toDomainString() })
        _sut.undo()
        _sut.undo()
        _sut.undo()
        assert(_sut.backupTextBodyState.value.isEmpty())
        assert(_sut.compileListFromEditStack().isEmpty())
        println(backup)
    }

    @Test
    fun `expected list sizes after three undos and two redos`() = runTest {
        val backup = _sut.backupTextBodyState
        _sut.initialize(arrayListOf())
        _sut.add(arrayListOf("One").map { it.toDomainString() })
        _sut.add(arrayListOf("Two").map { it.toDomainString() })
        _sut.add(arrayListOf("Three").map { it.toDomainString() })
        _sut.undo()
        _sut.undo()
        _sut.undo()
        _sut.redo()
        _sut.redo()
        assert(_sut.backupTextBodyState.value.size == 2)
        assert(_sut.compileListFromEditStack().size == 2)
        println(backup)
    }

    @Test
    fun `after three undos and three redos, the list should be the same as at beginning`() =
        runTest {
            _sut.initialize(arrayListOf())
            _sut.add(arrayListOf("One").map { it.toDomainString() })
            _sut.add(arrayListOf("Two").map { it.toDomainString() })
            _sut.add(arrayListOf("Three").map { it.toDomainString() })
            val backup = _sut.backupTextBodyState.value
            val compiled = _sut.compileListFromEditStack()
            _sut.undo()
            _sut.undo()
            _sut.undo()
            _sut.redo()
            _sut.redo()
            _sut.redo()
            println(backup)
            println(compiled)
            println(_sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
                .concatenateToOneString())
            println(_sut.compileListFromEditStack().map { SpannableStringBuilder(it.richString) }
                .concatenateToOneString())
            _sut.backupTextBodyState.value.forEachIndexed { index, item ->
                assert(
                    item.richString.contentEquals(
                        backup[index].richString
                    )
                )
            }
            _sut.compileListFromEditStack()
                .forEachIndexed { index, item -> assert(item.richString.contentEquals(compiled[index].richString)) }
        }

    @Test
    fun `Testing with concatenation 1`() = runTest {
        _sut.initialize(arrayListOf())
        println("Initialized: " + _sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(
            "Initialized: " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
        _sut.add(arrayListOf("One").map { it.toDomainString() })
        println("Added \"One\": " + _sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(
            "Added \"One\": " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
        _sut.add(arrayListOf("Two").map { it.toDomainString() })
        println("Added \"Two\": " + _sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(
            "Added \"Two\": " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
        _sut.add(arrayListOf("Three").map { it.toDomainString() })
        println("Added \"Three\": " + _sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(
            "Added \"Three\": " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
        _sut.undo()
        println("Undo once: " + _sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(
            "Undo once: " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
        _sut.undo()
        println("Undo twice: " + _sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(
            "Undo twice: " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
        _sut.undo()
        println("Undo three times: " + _sut.backupTextBodyState.value.map {
            SpannableStringBuilder(
                it.richString
            )
        }.concatenateToOneString())
        println(
            "Undo three times: " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
        _sut.redo()
        println("Redo once: " + _sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(
            "Redo once: " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
        _sut.redo()
        println("Redo twice: " + _sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(
            "Redo twice: " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
        _sut.add(arrayListOf("thr33", "f4or", "fiv5").map { it.toDomainString() })
        println("Final edit: " + _sut.backupTextBodyState.value.map { SpannableStringBuilder(it.richString) }
            .concatenateToOneString())
        println(
            "Final edit: " + _sut.compileListFromEditStack()
                .map { SpannableStringBuilder(it.richString) }.concatenateToOneString()
        )
    }
}