import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SkeletonShould {
    @Test
    public void pass_in_the_ci_server() {
        boolean skeletonIsWorking = true;
        assertThat(skeletonIsWorking).isTrue();
    }
}
