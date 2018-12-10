package com.carcksoft.spaceinvaders;

import com.carcksoft.spaceinvaders.domain.*;
import com.carcksoft.spaceinvaders.domain.constants.LayoutEntity;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class GameTest {

    @Test
    public void testTargettabilityAsIsCreisi() {

        Game game = new Game("id", new Sizing(25,25));

        game.setPlayerLocation(new Location(10,10));

        game.fillBoardVoids(new VisibleArea(7, 13, 7, 13));

        //game.relocatePhantom(Arrays.asList(new LocationWithMeta(10, 12)));

        assertEquals(Hazard.Targettability.DOWN, game.calculateTargettability(new Location(10, 11)));
        assertEquals(Hazard.Targettability.DOWN, game.calculateTargettability(new Location(10, 12)));
        assertEquals(Hazard.Targettability.DOWN, game.calculateTargettability(new Location(10, 13)));
        game.getBoard()[12][10] = LayoutEntity.WALL;
        assertEquals(Hazard.Targettability.NOPE, game.calculateTargettability(new Location(10, 13)));

        assertEquals(Hazard.Targettability.UP, game.calculateTargettability(new Location(10, 9)));
        assertEquals(Hazard.Targettability.UP, game.calculateTargettability(new Location(10, 8)));
        assertEquals(Hazard.Targettability.UP, game.calculateTargettability(new Location(10, 7)));
        game.getBoard()[8][10] = LayoutEntity.WALL;
        assertEquals(Hazard.Targettability.NOPE, game.calculateTargettability(new Location(10, 7)));

        assertEquals(Hazard.Targettability.LEFT, game.calculateTargettability(new Location(9, 10)));
        assertEquals(Hazard.Targettability.LEFT, game.calculateTargettability(new Location(8, 10)));
        assertEquals(Hazard.Targettability.LEFT, game.calculateTargettability(new Location(7, 10)));
        game.getBoard()[10][8] = LayoutEntity.WALL;
        assertEquals(Hazard.Targettability.NOPE, game.calculateTargettability(new Location(7, 10)));

        assertEquals(Hazard.Targettability.RIGHT, game.calculateTargettability(new Location(11, 10)));
        assertEquals(Hazard.Targettability.RIGHT, game.calculateTargettability(new Location(12, 10)));
        assertEquals(Hazard.Targettability.RIGHT, game.calculateTargettability(new Location(13, 10)));
        game.getBoard()[10][12] = LayoutEntity.WALL;
        assertEquals(Hazard.Targettability.NOPE, game.calculateTargettability(new Location(13, 10)));
    }

}
