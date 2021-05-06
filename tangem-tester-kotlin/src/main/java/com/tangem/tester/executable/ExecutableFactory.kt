package com.tangem.tester.executable

import com.tangem.tester.executable.asserts.Assert
import com.tangem.tester.executable.asserts.EqualsAssert
import com.tangem.tester.executable.steps.ScanStep
import com.tangem.tester.executable.steps.SignStep
import com.tangem.tester.executable.steps.Step

/**
 * Created by Anton Zhilenkov on 23/04/2021.
 */
interface ExecutableFactory : StepHolder, AssertHolder

interface AssertHolder {
    fun getAssert(name: String): Assert?
}

interface StepHolder {
    fun getStep(name: String): Step<*>?
}

class DefaultExecutableFactory : ExecutableFactory {

    private val testSteps = mutableMapOf<String, Step<*>>()
    private val asserts = mutableMapOf<String, Assert>()

    fun registerStep(executable: Step<*>) {
        testSteps[executable.getName()] = executable
    }

    override fun getStep(name: String): Step<*>? {
        return testSteps[name]
    }

    fun registerAssert(assert: Assert) {
        asserts[assert.getName()] = assert
    }

    override fun getAssert(name: String): Assert? {
        return asserts[name]
    }

    companion object {
        fun init(): ExecutableFactory {
            return DefaultExecutableFactory().apply {
                registerStep(SignStep())
                registerStep(ScanStep())
                registerAssert(EqualsAssert())
            }
        }
    }
}