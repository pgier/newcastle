package org.jboss.newcastle.model.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.newcastle.model.Project;
import org.junit.Test;

public class ProjectModelTest {

	@PersistenceContext
	EntityManager entityManager;

	@Test
	public void testProjectLookup() {

		Project project = entityManager.find(Project.class, new Integer(1));
		System.out.println( project.getName() );
		fail("Not yet implemented");
	}

}
