package rnd.tool.code.gen.impl.modern;

import java.io.Writer;

import rnd.tool.code.gen.core.impl.ClassInfoClassGenerator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.model.ElementInfo;

public class SpringServiceGenerator extends ClassInfoClassGenerator {

	@Override
	public String getClassSuffix() {
		return "";
	}

	@Override
	protected void writeImports(Writer writer, ClassInfo classInfo) {
		classInfo.getImports().add("import org.springframework.stereotype.Service;");
		classInfo.getImports().add("import org.springframework.beans.factory.annotation.Autowired;");
		super.writeImports(writer, classInfo);
	}

	@Override
	protected void writeClass(Writer writer, ClassInfo classInfo) {
		writeln(writer, "@Service");
		super.writeClass(writer, classInfo);
	}

	@Override
	protected void writeField(Writer writer, ClassInfo classInfo, ElementInfo field) {
		writeln(writer, "\t@Autowired");
		super.writeField(writer, classInfo, field);
	}

}