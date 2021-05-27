package rnd.tool.code.mig.impl.modern;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import rnd.tool.code.mig.core.utils.MigrationHelper;

public class RestTest {

	private static Path inPath = Paths.get("../samples/MyServlet/src/main/java");
	private static Path outPath = Paths.get("../samples/MyServlet/gen/main/java");

	@Test
	public void testD2R() throws IOException {
		Dao2Repo mig = new Dao2Repo();
		MigrationHelper.migrate(mig, inPath, outPath, "my.webapp.dao.PojoDao");
	}

	@Test
	public void testS2S() {
		Singleton2SpringService mig = new Singleton2SpringService();
		MigrationHelper.migrate(mig, inPath, outPath, "my.webapp.service.PojoService");
	}

	@Test
	public void testS2RSpring() {
		// Servlet2Rest mig = new Servlet2Rest();
		Servlet2SpringRest mig = (Servlet2SpringRest) new Servlet2SpringRest().child(new Request2Param());
		MigrationHelper.migrate(mig, inPath, outPath, "my.webapp.servlet.PojoServlet");
	}

	@Test
	public void test1S2RJee() {

		Path inPath = Paths.get("../samples/MyServlet/src/main/java");
		Path outPath = Paths.get("../samples/MyServlet/gen/main/java");
		String className = "my.webapp.servlet.PojoServlet";
		Servlet2JeeRest mig = (Servlet2JeeRest) new Servlet2JeeRest().child(new Request2Param());

		MigrationHelper.migrate(mig, inPath, outPath, className);
	}

	//@Test
	public void test2S2RJee() {

		Path inPath = Paths.get("../samples/weblogic/gen/webapp");
		Path outPath = Paths.get("../samples/weblogic/gen2/webapp");
		String className = "SampleWebLogicServlet";

		Servlet2JeeRest mig = (Servlet2JeeRest) new Servlet2JeeRest().child(new Request2Param());

		MigrationHelper.migrate(mig, inPath, outPath, className);
	}

}
