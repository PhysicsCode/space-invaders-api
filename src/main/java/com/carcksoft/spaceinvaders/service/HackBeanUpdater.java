package com.carcksoft.spaceinvaders.service;

import com.carcksoft.spaceinvaders.domain.Game;
import com.carcksoft.spaceinvaders.domain.Hazard;
import com.carcksoft.spaceinvaders.domain.constants.LayoutEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HackBeanUpdater {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void updateHackBean(HackBean hackBean, Game game, Boolean canFire) {

        hackBean.setCanFire(String.valueOf(canFire));

        hackBean.setAsciiBoard(calculateAscii(game));

        sendMapUpdates(hackBean);
        sendFireUpdates(hackBean);
    }


    private String calculateAscii(Game game) {

        LayoutEntity[][] board = cloneArray(game.getBoard());

        board[game.getPlayerLocation().getY()][game.getPlayerLocation().getX()] = LayoutEntity.MYSELF;

        game.getPhantomSet().stream().map(Hazard::getLocation).forEach(location -> board[location.getY()][location.getX()] = LayoutEntity.PHANTOM);
        game.getEnemyPlayerSet().stream().map(Hazard::getLocation).forEach(location -> board[location.getY()][location.getX()] = LayoutEntity.PLAYER);

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i<board[0].length; i++) {

            for (int j = 0; j< board.length; j++) {

                switch(board[j][i]) {

                    case WALL:
                        buffer.append("■");
                        break;
                    case EMPTY:
                        buffer.append(" ");
                        break;
                    case UNKNOWN:
                        buffer.append("□");
                        break;
                    case PLAYER:
                        buffer.append("x");
                        break;
                    case PHANTOM:
                        buffer.append("◎");
                        break;
                    case NEUTRAL_PHANTOM:
                        buffer.append("●");
                        break;
                    case MYSELF:
                        buffer.append("☆");
                        break;
                }
            }

            buffer.append("<br>");
        }

        return buffer.toString();
    }

    private void sendMapUpdates(HackBean hackBean) {
        simpMessagingTemplate.convertAndSend("/topic/map", hackBean.getAsciiBoard());
    }

    private void sendFireUpdates(HackBean hackBean){

        simpMessagingTemplate.convertAndSend("topic/fire", hackBean.getCanFire());
    }

    private LayoutEntity[][] cloneArray(LayoutEntity[][] src) {
        int length = src.length;
        LayoutEntity[][] target = new LayoutEntity[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }
}
