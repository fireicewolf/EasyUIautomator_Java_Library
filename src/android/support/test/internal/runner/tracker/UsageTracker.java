package android.support.test.internal.runner.tracker;

/**
 * Used by test infrastructure to report usage stats (optionally).
 * <p/>
 * This interface should only be used by test infrastructure.
 */
public interface UsageTracker {

    /**
     * Indicates that a particular tool/api was used.
     * <p/>
     * Usage will be dumped at the end of the instrumentation run.
     */
    void trackUsage(String usage);

    /**
     * Requests that all usages be sent.
     */
    void sendUsages();

    /**
     * NoOp implementation.
     */
    class NoOpUsageTracker implements UsageTracker {
        @Override
        public void trackUsage(String unused) {
        }

        @Override
        public void sendUsages() {
        }
    }
}