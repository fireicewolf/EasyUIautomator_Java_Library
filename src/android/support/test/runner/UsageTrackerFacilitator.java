/*
 * Copyright (C) 2016 The Android Open Source Project
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

package android.support.test.runner;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.internal.runner.RunnerArgs;
import android.support.test.internal.runner.tracker.UsageTracker;
import android.support.test.internal.runner.tracker.UsageTrackerRegistry;
import android.util.Log;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * Helper class to enable/disable usage tracker in the runner. For details on how AndroidJUnitRunner
 * tracks usage, see
 * <a href="https://google.github.io/android-testing-support-library/docs/espresso/setup/index.html#analytics">
 * AndroidJUnitRunner Analytics</a>
 */
public class UsageTrackerFacilitator implements UsageTracker{
    private static final String TAG = "UsageTrackerFacilitator";

    private final RunnerArgs mRunnerArgs;

    public UsageTrackerFacilitator(@NonNull RunnerArgs runnerArgs) {
        mRunnerArgs = checkNotNull(runnerArgs, "runnerArgs cannot be null!");
    }

    public boolean shouldTrackUsage() {
        return !mRunnerArgs.disableAnalytics;
    }

    public void registerUsageTracker(@Nullable UsageTracker usageTracker) {
        if (usageTracker != null && shouldTrackUsage()) {
            Log.i(TAG, "Usage tracking enabled");
            UsageTrackerRegistry.registerInstance(usageTracker);
            return;
        }
        // Even though this is the default usage tracker we should not rely on internal
        // implementation details of UsageTrackerRegistry
        Log.i(TAG, "Usage tracking disabled");
        UsageTrackerRegistry.registerInstance(new NoOpUsageTracker());
    }

    @Override
    public void trackUsage(String usage) {
        if (shouldTrackUsage()) {
            UsageTrackerRegistry.getInstance().trackUsage(usage);
        }
    }

    @Override
    public void sendUsages() {
        if (shouldTrackUsage()) {
            UsageTrackerRegistry.getInstance().sendUsages();
        }
    }
}
