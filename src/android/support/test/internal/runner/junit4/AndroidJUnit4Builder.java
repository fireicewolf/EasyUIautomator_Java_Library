/*
 * Copyright (C) 2015 The Android Open Source Project
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
package android.support.test.internal.runner.junit4;

import android.util.Log;
import android.support.test.internal.util.AndroidRunnerParams;

import org.junit.internal.builders.JUnit4Builder;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

/**
 * A {@link RunnerBuilder} that will build customized runners needed to handle the ability to skip
 * test execution if needed.
 */
public class AndroidJUnit4Builder extends JUnit4Builder {

    private static final String LOG_TAG = "AndroidJUnit4Builder";
    private final AndroidRunnerParams mAndroidRunnerParams;

    /**
     * @param runnerParams {@link AndroidRunnerParams} that stores common runner parameters
     */
    public AndroidJUnit4Builder(AndroidRunnerParams runnerParams) {
        mAndroidRunnerParams = runnerParams;
    }

    @Override
    public Runner runnerForClass(Class<?> testClass) throws Throwable {
        try {
            // check if we need to skip execution and return an appropriate runner.
            if (mAndroidRunnerParams.isSkipExecution()) {
                // we don't check for junit3 here because the AndroidJUnit3Builder would already
                // have picked up the test class. See AllDefaultPossibilitiesBuilder for details.
                return new NonExecutingJUnit4ClassRunner(testClass);
            }
            return new AndroidJUnit4ClassRunner(testClass, mAndroidRunnerParams);
        } catch (Throwable e) {
            // log error message including stack trace before throwing to help with debugging.
            Log.e(LOG_TAG, "Error constructing runner", e);
            throw e;
        }
    }
}
