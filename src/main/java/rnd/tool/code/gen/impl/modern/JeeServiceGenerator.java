package rnd.tool.code.gen.impl.modern;

import java.io.Writer;

import rnd.tool.code.gen.core.impl.ClassInfoClassGenerator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.model.ElementInfo;

public class JeeServiceGenerator extends ClassInfoClassGenerator {

	@Override
	public String getClassSuffix() {
		return "";
	}

	@Override
	protected void writeImports(Writer writer, ClassInfo classInfo) {
		writeln(writer, "import javax.inject.Inject;");
		writeln(writer, "import javax.inject.Singleton;");
		super.writeImports(writer, classInfo);
	}

	@Override
	protected void writeClass(Writer writer, ClassInfo classInfo) {
		writeln(writer, "\t@Singleton");
		super.writeClass(writer, classInfo);
	}

	@Override
	protected void writeField(Writer writer, ClassInfo classInfo, ElementInfo field) {
		writeln(writer, "\t@Inject");
		super.writeField(writer, classInfo, field);
	}

}