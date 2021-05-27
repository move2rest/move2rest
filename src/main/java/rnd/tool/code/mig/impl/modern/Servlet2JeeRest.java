package rnd.tool.code.mig.impl.modern;

import rnd.tool.code.gen.impl.modern.JeeRestGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.parse.impl.modern.ServletParser;

public class Servlet2JeeRest extends AbstractMigrator<ClassInfo> {

	public Servlet2JeeRest() {
		this.classPar = new ServletParser();
		this.classGen = new JeeRestGenerator();
	}

}
