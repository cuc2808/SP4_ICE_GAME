package Main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        // Verdens-areal
        int leftX   = entity.worldX + entity.solidArea.x;
        int rightX  = leftX + entity.solidArea.width;
        int topY    = entity.worldY + entity.solidArea.y;
        int bottomY = topY + entity.solidArea.height;

        // Kolonner og rækker
        int leftCol   = leftX / gp.tileSize;
        int rightCol  = rightX / gp.tileSize;
        int topRow    = topY / gp.tileSize;
        int bottomRow = bottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {

            case "up":
                topRow = (topY - entity.movementSpeed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[topRow][leftCol];
                tileNum2 = gp.tileM.mapTileNum[topRow][rightCol];
                break;

            case "down":
                bottomRow = (bottomY + entity.movementSpeed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[bottomRow][leftCol];
                tileNum2 = gp.tileM.mapTileNum[bottomRow][rightCol];
                break;

            case "left":
                leftCol = (leftX - entity.movementSpeed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[topRow][leftCol];
                tileNum2 = gp.tileM.mapTileNum[bottomRow][leftCol];
                break;

            case "right":
                rightCol = (rightX + entity.movementSpeed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[topRow][rightCol];
                tileNum2 = gp.tileM.mapTileNum[bottomRow][rightCol];
                break;

            case "upLeft":
                topRow = (topY - entity.movementSpeed) / gp.tileSize;
                leftCol = (leftX - entity.movementSpeed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[topRow][leftCol];
                tileNum2 = gp.tileM.mapTileNum[topRow][rightCol];
                break;

            case "upRight":
                topRow = (topY - entity.movementSpeed) / gp.tileSize;
                rightCol = (rightX + entity.movementSpeed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[topRow][leftCol];
                tileNum2 = gp.tileM.mapTileNum[topRow][rightCol];
                break;

            case "downLeft":
                bottomRow = (bottomY + entity.movementSpeed) / gp.tileSize;
                leftCol = (leftX - entity.movementSpeed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[bottomRow][leftCol];
                tileNum2 = gp.tileM.mapTileNum[bottomRow][rightCol];
                break;

            case "downRight":
                bottomRow = (bottomY + entity.movementSpeed) / gp.tileSize;
                rightCol = (rightX + entity.movementSpeed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[bottomRow][leftCol];
                tileNum2 = gp.tileM.mapTileNum[bottomRow][rightCol];
                break;

            default:
                return;
        }

        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }
    public int checkObject(Entity entity, boolean player){
    int index =  999;

    for(int i = 0; i < gp.objArray.length; i++){
        if(gp.objArray[i] != null){

            //Get  entity's solid area position
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;
            //Get the object's solid area position
            gp.objArray[i].solidArea.x = gp.objArray[i].worldX + gp.objArray[i].solidArea.x;
            gp.objArray[i].solidArea.y = gp.objArray[i].worldY + gp.objArray[i].solidArea.y;

            switch(entity.direction){
                case "up":
                    entity.solidArea.y -= entity.movementSpeed;
                    if(entity.solidArea.intersects(gp.objArray[i].solidArea)) {
                        if(gp.objArray[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "upLeft":
                    entity.solidArea.y -= entity.movementSpeed;
                    if(entity.solidArea.intersects(gp.objArray[i].solidArea)) {
                        if(gp.objArray[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                case "upRight":
                    entity.solidArea.y -= entity.movementSpeed;
                    if(entity.solidArea.intersects(gp.objArray[i].solidArea)) {
                        if(gp.objArray[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "down":
                    entity.solidArea.y -= entity.movementSpeed;
                    if(entity.solidArea.intersects(gp.objArray[i].solidArea)) {
                        if(gp.objArray[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "downLeft":
                    entity.solidArea.y -= entity.movementSpeed;
                    if(entity.solidArea.intersects(gp.objArray[i].solidArea)) {
                        if(gp.objArray[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "downRight":
                    entity.solidArea.y -= entity.movementSpeed;
                    if(entity.solidArea.intersects(gp.objArray[i].solidArea)) {
                        if(gp.objArray[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "left":
                    entity.solidArea.y -= entity.movementSpeed;
                    if(entity.solidArea.intersects(gp.objArray[i].solidArea)) {
                        if(gp.objArray[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "right":
                    entity.solidArea.y -= entity.movementSpeed;
                    if(entity.solidArea.intersects(gp.objArray[i].solidArea)) {
                        if(gp.objArray[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.objArray[i].solidArea.x = gp.objArray[i].solidAreaDefaultX;
            gp.objArray[i].solidArea.y = gp.objArray[i].solidAreaDefaultY;
        }
    }

    return index;
    }
}
