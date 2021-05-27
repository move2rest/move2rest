package rnd.tool.code.mig.impl.modern;

import rnd.tool.code.gen.core.impl.ClassInfoClassGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.parse.impl.modern.RequestParamParser;

public class Request2Param extends AbstractMigrator<ClassInfo> {

	public Request2Param() {
		this.classPar = new RequestParamParser();
	}

	@Override
	public void migrate() {

		if (getParent() != null) {
			this.classGen = getParent().getClassGenerator();

			getClassParser().setClassSuffix(getClassGenerator().getClassSuffix());
			getClassParser().setPath(getClassGenerator().getPath());
			getClassParser().setClassName(getClassGenerator().getClassName());

			getClassParser().setSourceCode(getClassGenerator().getSourceCode());
		} else {
			this.classGen = new ClassInfoClassGenerator();
		}

		super.migrate();
	}

}
