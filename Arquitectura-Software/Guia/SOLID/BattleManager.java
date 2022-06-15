import java.util.Random;

class BattleManager {
    Player player1;
    Enemy player2;

    public BattleManager(Player player1, Enemy player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startDuel() {
        while(player1.life > 0) {
            player1.attack(player2);
            if(player2.life < 1)
                return "Has ganado la batalla";
            else
                player2.attack(player1);
        }
    }
}

class Entity {
    int life = 100;
}

class Player extends Entity {
    public void attack(Entity opponent) {
        Random r = new Random();
        int attack = r.nextInt(5) + 1;
        opponent.life -= attack;
    }
}

class Enemy extends Entity {
    public void attack(Entity opponent) {
        Random r = new Random();
        int attack = r.nextInt(4);
        opponent.life -= attack;
    }
}
