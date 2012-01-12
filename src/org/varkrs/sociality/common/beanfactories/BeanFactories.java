package org.varkrs.sociality.common.beanfactories;

import org.springframework.context.ApplicationContext;
import org.varkrs.sociality.common.web.configurations.ProjectWebContext;

/**如果你想知道如何获取各个子项目的Bean工厂(最好不要自己手动new一个实例), 从这里就可以找到答案.
 * 
 */
public class BeanFactories {

	public static ApplicationContext getLocalApplicationContext() {
		return (ApplicationContext) ProjectWebContext
				.getWebApplicationContext().getBean(
						"localApplicationContext");
	}

}
