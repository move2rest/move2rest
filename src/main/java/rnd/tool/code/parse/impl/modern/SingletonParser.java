package rnd.tool.code.parse.impl.modern;

import java.util.Map.Entry;

import com.github.javaparser.ast.body.MethodDeclaration;

import rnd.tool.code.model.ClassInfoImpl;
import rnd.tool.code.model.MethodInfo;
import rnd.tool.code.parse.core.impl.ClassInfoClassParser;

public class SingletonParser extends ClassInfoClassParser {

	// TODO : Replace DAO call with Repository calls
	@Override
	protected MethodInfo processMethod(ClassInfoImpl classInfo, Entry<String, MethodDeclaration> method) {

		// Skip instance method ( type same as enclosing type )
		if (method.getValue().getType().toString().equals(classInfo.getName() + getClassSuffix())) {
			return null;
		}

		return super.processMethod(classInfo, method);
	}

}