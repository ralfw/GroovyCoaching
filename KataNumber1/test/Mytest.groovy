import org.junit.Test
import org.testng.Assert
import klassen.*

/**
 * Created by ralfw on 22.12.14.
 */

class Mytest {
    @Test
    void losGehts() {
        def p = new Person()
        p.name = "maria"
        Assert.assertEquals("maria", p.name)
    }
}
