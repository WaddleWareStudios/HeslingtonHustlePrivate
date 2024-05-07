package com.main.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * The CollisionHandler class handles collisions for each layer called through.
 * A layer has tiles that the player should collide with if the layer has the property "blocked"
 * and determines the resulting position after collisions have been resolved.
 */
public class CollisionHandler {
    private final TiledMap tiledMap;
    private final int tileWidth, tileHeight;

    private final float objWidth, objHeight;
    private final float offSetX, offSetY;
    private final ArrayList<TiledMapTileLayer> collisionLayers;

    /**
     * Constructs a CollisionHandler with specified parameters.
     *
     * @param tiledMap The TiledMap in which the collisions are checked.
     * @param tileWidth The width of a single tile in the map.
     * @param tileHeight The height of a single tile in the map.
     * @param objWidth The width of the object checking for collisions.
     * @param objHeight The height of the object checking for collisions.
     * @param scaleX The scale factor along the X-axis for the object.
     * @param scaleY The scale factor along the Y-axis for the object.
     */
    public CollisionHandler(TiledMap tiledMap, int tileWidth, int tileHeight, float objWidth, float objHeight, float scaleX, float scaleY){
        this.tiledMap = tiledMap;
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.objWidth = objWidth * scaleX;
        this.objHeight = objHeight * scaleY;
        this.offSetX = (objWidth - this.objWidth) / 2;
        this.offSetY = (objHeight - this.objHeight) / 2;
        this.collisionLayers = new ArrayList<>();
    }

    /**
     * Adds collision layers by name to the handler for collision detection.
     *
     * @param args The names of the TiledMapTileLayer(s) to be added for collision detection.
     */
    public void addCollisionLayers(String... args){
        for (String layer : args) {
            collisionLayers.add((TiledMapTileLayer) tiledMap.getLayers().get(layer));
        }
    }

    /**
     * Detects whether the object is touching a tile of a specific layer.
     *
     * @param layerName The name of the layer.
     * @param obj The rectangle representing the object's position and size.
     * @return A Boolean indicating whether the object is touching a tile of the layer
     */
    public boolean isTouching(String layerName, Rectangle obj){
        Vector2 bottomLeft = new Vector2 (Math.floorDiv((int) obj.x, tileWidth), Math.floorDiv((int) obj.y, tileHeight));
        Vector2 topRight = new Vector2 (Math.floorDiv((int) (obj.x + obj.width), tileWidth), Math.floorDiv((int) (obj.y + obj.height), tileHeight));
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(layerName);

        for (int i = (int) bottomLeft.x; i <= topRight.x; i++) {
            for (int j = (int) bottomLeft.y; j <= topRight.y; j++) {
                TiledMapTileLayer.Cell cell = layer.getCell(i, j);
                if (cell != null){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determines the first side hit during movement in a specified direction.
     *
     * @param x The X-coordinate of the object's position.
     * @param y The Y-coordinate of the object's position.
     * @param obj The rectangle representing the object's position and size.
     * @param dir The direction of movement.
     * @return A Vector2 indicating the side hit and the depth of collision; null if no collision occurred.
     */
    public Vector2 getSideHit(float x, float y, Rectangle obj, int dir){
        Vector2 bottomLeft = new Vector2 (Math.floorDiv((int) x, tileWidth), Math.floorDiv((int) y, tileHeight));
        Vector2 topRight = new Vector2 (Math.floorDiv((int) (x + objWidth), tileWidth), Math.floorDiv((int) (y + objHeight), tileHeight));
        Vector2 firstSide = null;

        for (int i = (int) bottomLeft.x; i <= topRight.x; i++) {
            for (int j = (int) bottomLeft.y; j <= topRight.y; j++) {
                for (TiledMapTileLayer layer : collisionLayers) {
                    TiledMapTileLayer.Cell cell = layer.getCell(i, j);
                    if (cell != null) {
                        Vector2 side = collidingSide(obj, tileToRect(i, j), dir);
                        if (side != null && (firstSide == null || side.x < firstSide.x) && side.x >= 0) {
                            firstSide = side;
                        }
                        break;
                    }
                }
            }
        }

        return firstSide;
    }

    /**
     * Converts tile coordinates to a rectangle in world space.
     *
     * @param tileX The X-coordinate of the tile.
     * @param tileY The Y-coordinate of the tile.
     * @return A rectangle representing the tile's position and size in world space.
     */
    public Rectangle tileToRect(int tileX, int tileY){
        return new Rectangle(tileX * tileWidth, tileY * tileHeight, tileWidth, tileHeight);
    }

    /**
     * Determines the direction of movement from a start position to a target position.
     *
     * @param startX The starting X-coordinate.
     * @param startY The starting Y-coordinate.
     * @param targX The target X-coordinate.
     * @param targY The target Y-coordinate.
     * @return An integer representing the direction of movement.
     */
    public int getDirection(float startX, float startY, float targX, float targY){
        int dir = 0;
        if (startY > targY){
            dir += 1;
        }
        else if (startY < targY){
            dir += 2;
        }
        if (startX > targX){
            dir += 3;
        }
        else if (startX < targX) {
            dir += 6;
        }
        return dir;
    }

    /**
     * Determines the colliding side between two rectangles during movement in a specified direction.
     *
     * @param movingObj The rectangle representing the moving object.
     * @param other The rectangle representing the object being collided with.
     * @param dir The direction of movement.
     * @return A Vector2 indicating the colliding side and the depth of collision; null if no collision occurred.
     */
    public Vector2 collidingSide(Rectangle movingObj, Rectangle other, int dir){
        Vector2 coll1, coll2, coll3, coll4;

        switch (dir){
            case 1:
                coll1 = new Vector2(movingObj.y - (other.y + other.height), 0);
                return coll1;
            case 2:
                coll2 = new Vector2(other.y - (movingObj.y + movingObj.height), 1);
                return coll2;
            case 3:
                coll3 = new Vector2(movingObj.x - (other.x + other.width), 2);
                return coll3;
            case 4:
                coll1 = new Vector2(movingObj.y - (other.y + other.height), 0);
                coll3 = new Vector2(movingObj.x - (other.x + other.width), 2);
                return coll1.x > coll3.x ? coll1 : coll3;
            case 5:
                coll2 = new Vector2(other.y - (movingObj.y + movingObj.height), 1);
                coll3 = new Vector2(movingObj.x - (other.x + other.width), 2);
                return coll2.x > coll3.x ? coll2 : coll3;
            case 6:
                coll4 = new Vector2(other.x - (movingObj.x + movingObj.width), 3);
                return coll4;
            case 7:
                coll1 = new Vector2(movingObj.y - (other.y + other.height), 0);
                coll4 = new Vector2(other.x - (movingObj.x + movingObj.width), 3);
                return coll1.x > coll4.x ? coll1 : coll4;
            case 8:
                coll2 = new Vector2(other.y - (movingObj.y + movingObj.height), 1);
                coll4 = new Vector2(other.x - (movingObj.x + movingObj.width), 3);
                return coll2.x > coll4.x ? coll2 : coll4;
            default:
                return null;
        }
    }

    /**
     * Adjusts the position of the object for a single movement step, considering potential collisions.
     *
     * @param startX The starting X-coordinate.
     * @param startY The starting Y-coordinate.
     * @param targX The target X-coordinate.
     * @param targY The target Y-coordinate.
     * @return A Vector2 representing the adjusted position; null if no adjustment is necessary.
     */
    public Vector2 adjustPosStep(float startX, float startY, float targX, float targY){
        Rectangle obj = new Rectangle(startX, startY, objWidth, objHeight);
        int dir = getDirection(startX, startY, targX, targY);
        Vector2 side = getSideHit(targX, targY, obj, dir);
        if (side == null) {return null;}
        int sideNum = (int) side.y;
        switch(sideNum) {
            case 0:
                return new Vector2(targX, startY - side.x + 1);
            case 1:
                return new Vector2(targX, startY + side.x - 1);
            case 2:
                return new Vector2(startX - side.x + 1, targY);
            case 3:
                return new Vector2(startX + side.x - 1, targY);
            default:
                return null;
        }
    }

    /**
     * Continuously adjusts the position of the object from start to target, resolving any collisions along the way.
     *
     * @param startX The starting X-coordinate.
     * @param startY The starting Y-coordinate.
     * @param targX The target X-coordinate.
     * @param targY The target Y-coordinate.
     * @return A Vector2 representing the final adjusted position after resolving all collisions.
     */
    public Vector2 adjustPos(float startX, float startY, float targX, float targY){
        startX += offSetX; startY += offSetY; targX += offSetX; targY += offSetY;
        Vector2 nextPos = adjustPosStep(startX, startY, targX, targY);
        Vector2 newPos = null;
        while (nextPos != null){
            newPos = nextPos;
            nextPos = adjustPosStep(startX, startY, nextPos.x, nextPos.y);
        }
        if (newPos != null){
            newPos.x -= offSetX; newPos.y -= offSetY;
        }
        else {
            newPos = new Vector2(targX - offSetX, targY - offSetY);
        }
        return newPos;
    }
}
