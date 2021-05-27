package rnd.tool.code.gen.impl.modern;

import java.io.Writer;
import java.util.Set;

import rnd.tool.code.gen.core.impl.ClassInfoRestGenerator;
import rnd.tool.code.model.ClassInfo;
import rnd.tool.code.model.ClassInfoImpl;
import rnd.tool.code.model.ElementInfoImpl;
import rnd.tool.code.model.MethodInfoImpl;

public class JeeJmsGenerator extends ClassInfoRestGenerator {

	public static final String CLASS_SUFFIX = "Listner";

	@Override
	public String getClassSuffix() {
		return CLASS_SUFFIX;
	}

	@Override
	protected void writeImports(Writer writer, ClassInfo classInfo) {
		Set<String> imports = classInfo.getImports();
		imports.add("import javax.jms.rs.JMSDestinationDefinition;");
		imports.add("import javax.jms.rs.JMSConnectionFactoryDefinition;");
		super.writeImports(writer, classInfo);
	}

	@Override
	protected void writeClass(Writer writer, ClassInfo classInfo) {
		writeln(writer, "@JMSDestinationDefinition(\"\")");
		writeln(writer, "@JMSConnectionFactoryDefinition(\"name\"=\"\")");
		classInfo.getExtensions().add(new ClassInfoImpl("MessageListener"));
		MethodInfoImpl method = new MethodInfoImpl("onMessage");
		method.addParam(new ElementInfoImpl("Message", "msg"));
		classInfo.getMethods().add(method);
		super.writeClass(writer, classInfo);
	}

}