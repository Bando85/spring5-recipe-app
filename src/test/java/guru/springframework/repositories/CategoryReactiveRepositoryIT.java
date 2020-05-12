package guru.springframework.repositories;

import guru.springframework.bootstrap.DevBootstrap;
import guru.springframework.domain.Category;
import guru.springframework.repositories.reactive.CategoryReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository reactiveRepository;

    @Before
    public void setUp() throws Exception {
        reactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveDocument() {
        Category cat1 = new Category();
        cat1.setDescription("American");
        Category savedCategory = reactiveRepository.save(cat1).block();

        Category category = reactiveRepository.findById(savedCategory.getId()).block();
        Long count = reactiveRepository.count().block();

        assertEquals(savedCategory.getId(), category.getId());
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription() {
        Category cat1 = new Category();
        cat1.setDescription("American");
        Category savedCategory = reactiveRepository.save(cat1).block();

        Category category = reactiveRepository.findByDescription("American").block();
        Long count = reactiveRepository.count().block();

        assertEquals(savedCategory.getId(), category.getId());
        assertEquals(Long.valueOf(1L), count);
    }

}
