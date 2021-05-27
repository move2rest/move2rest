package rnd.tool.code.mig.impl.modern;

import rnd.tool.code.gen.impl.modern.SpringRestGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.parse.impl.modern.ServletParser;

public class Servlet2SpringRest extends AbstractMigrator<ClassInfo> {

	public Servlet2SpringRest() {
		this.classPar = new ServletParser();
		this.classGen = new SpringRestGenerator();
	}

}
