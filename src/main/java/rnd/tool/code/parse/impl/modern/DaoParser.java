package rnd.tool.code.parse.impl.modern;

import java.util.Map.Entry;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VoidType;

import rnd.tool.code.model.ClassInfoImpl;
import rnd.tool.code.model.MethodInfo;
import rnd.tool.code.model.MethodInfo.HttpMethod;
import rnd.tool.code.parse.core.impl.ClassInfoClassParser;
import rnd.tool.code.parse.core.utils.HttpMethodMapper;

public class DaoParser extends ClassInfoClassParser {

	public static final String CLASS_SUFFIX = "Dao";

	@Override
	public String getClassSuffix() {
		return CLASS_SUFFIX;
	}

	@Override
	protected void parseClassName(ClassInfoImpl classInfo, String className) {
		classInfo.setType(rnd.tool.code.model.ClassInfo.Type.Interface);
		super.parseClassName(classInfo, className);
	}

	@Override
	protected MethodInfo processMethod(ClassInfoImpl classInfo, Entry<String, MethodDeclaration> method) {

		String name = method.getKey();
		MethodDeclaration methodDec = method.getValue();
		Type type = methodDec.getType();

		// Skip instance method ( type same as enclosing type )
		if (type.toString().equals(classInfo.getName() + getClassSuffix())) {
			return null;
		}

		// Parse the method name
		HttpMethod httpMethod = HttpMethodMapper.map(name);
		// Skip CRUD methods ( having http method and type as void or same as class )
		if (httpMethod != null && (type instanceof VoidType || type.toString().equals(classInfo.getName()))) {
			return null;
		}

		return super.processMethod(classInfo, method);
	}

}
