package it.unibo.oop.bbgmm.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;

import it.unibo.oop.bbgmm.utilities.ComponentsContainer;
import it.unibo.oop.bbgmm.utilities.ComponentsContainerImpl;
import org.junit.Test;


interface Parent {
}

interface ChildA extends Parent {
}

interface ChildB extends Parent {
}

interface ChildofAB extends ChildA, ChildB {
}

class ConcreteParent implements Parent {
}

class ConcreteChildA implements ChildA {
}

/**
 * Test for component container
 */
public class ComponentBagTest {

    /**
     * It must not be possible to add the parent interface to the bag. It would make no sense and it would "fill" the
     * whole bag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddParent() {
        final ComponentsContainer<Parent> bag = new ComponentsContainerImpl<>(Parent.class);

        bag.put(new Parent() {
        });
    }

    /**
     * Basic test for put and get.
     */
    @Test
    public void testPut() {
        final ComponentsContainer<Parent> bag = new ComponentsContainerImpl<>(Parent.class);
        final Parent a = new ChildA() {
        };
        final Parent b = new ChildB() {
        };
        bag.put(a);
        bag.put(b);

        assertEquals("The element a is not present",true, bag.get(ChildA.class).isPresent());
        assertEquals("Should have returned element a but returned something else",a, bag.get(ChildA.class).get());
        assertEquals("The element b is not present",true, bag.get(ChildB.class).isPresent());
        assertEquals("Should have returned element a but returned something else",b, bag.get(ChildB.class).get());
    }

    /**
     * Must not be possible as it would make no sense.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParentCannotBeConcreteClass() {
        new ComponentsContainerImpl<>(ConcreteParent.class);
    }

    /**
     * Must not be possible since this is a bag of Interfaces.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCannotGetByConcreteClass() {
        final ComponentsContainer<Parent> bag = new ComponentsContainerImpl<>(Parent.class);
        final Parent a = new ConcreteChildA();
        final Parent b = new ChildB() {
        };
        bag.put(b);
        bag.put(a);

        bag.get(ConcreteChildA.class);
    }

    /**
     * It must not be possible to get elements by the parent interface.
     * (What element would we return?)
     */
    @Test
    public void testParentNotPresent() {
        final ComponentsContainer<Parent> bag = new ComponentsContainerImpl<>(Parent.class);

        bag.put(new ChildA() {
        });

        assertEquals("It returned an element by the parent interface",false, bag.get(Parent.class).isPresent());
    }

    /**
     * Remove a specific element.
     */
    @Test
    public void testRemoveByElement() {
        final ComponentsContainer<Parent> bag = new ComponentsContainerImpl<>(Parent.class);
        final ChildA a = new ChildA() {
        };
        final ChildB b = new ChildB() {
        };
        bag.put(b);
        bag.put(a);
        bag.remove(a);

        assertEquals("The element b is not present",true, bag.get(ChildB.class).isPresent());
        assertEquals("The element id still in the map",false, bag.get(ChildA.class).isPresent());
    }

    /**
     * Remove elements by the interface.
     */
    @Test
    public void testRemoveByInterface() {
        final ComponentsContainer<Parent> bag = new ComponentsContainerImpl<>(Parent.class);
        final ChildA a = new ChildA() {
        };
        final ChildB b = new ChildB() {
        };
        bag.put(b);
        bag.put(a);
        bag.remove(ChildA.class);

        assertEquals("The element b is not present",true, bag.get(ChildB.class).isPresent());
        assertEquals("The element a is still in the map",false, bag.get(ChildA.class).isPresent());
    }

    /**
     * Here we test adding an element implementing multiple interfaces.
     */
    @Test
    public void testSubInterfaces() {
        final ComponentsContainer<Parent> bag = new ComponentsContainerImpl<>(Parent.class);
        final ChildofAB ab = new ChildofAB() {
        };

        bag.put(ab);

        assertEquals("The element ab is not present",true, bag.get(ChildA.class).isPresent());
        assertEquals("Should have returned element ab but returned something else",ab, bag.get(ChildA.class).get());
        assertEquals("The element ab is not present",true, bag.get(ChildB.class).isPresent());
        assertEquals("Should have returned element ab but returned something else",ab, bag.get(ChildB.class).get());
        assertEquals("The element ab is not present",true, bag.get(ChildofAB.class).isPresent());
        assertEquals("Should have returned element a but returned something else",ab, bag.get(ChildofAB.class).get());
    }

    /**
     * Cannot add the same interface more than once.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInterfacesClash() {
        final ComponentsContainer<Parent> bag = new ComponentsContainerImpl<>(Parent.class);
        final ChildA a = new ChildA() {
        };
        final ChildofAB ab = new ChildofAB() {
        };

        bag.put(ab);
        bag.put(a);
    }

    /**
     * Here we test the stream method.
     */
    @Test
    public void testStream() {
        final ComponentsContainer<Parent> bag = new ComponentsContainerImpl<>(Parent.class);
        final ChildA a = new ChildA() {
        };
        final ChildB b = new ChildB() {
        };

        bag.put(b);
        bag.put(a);

        assertEquals("The list obtained from the stream is different from (b,a)"
                ,bag.stream().collect(Collectors.toList()), Arrays.asList(b, a));
    }
}

