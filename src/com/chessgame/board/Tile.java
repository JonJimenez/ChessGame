package com.chessgame.board;
import java.util.HashMap;
import java.util.Map;

import com.chessgame.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {
	protected final int tileCoordinate;
	private static final Map<Integer,EmptyTile> EMPTY_TILES = createEmptyChessBoard();
	
	//Initialzes the Chess board with Empty Chess Hashmap that contains no pieces
	private static Map<Integer,EmptyTile> createEmptyChessBoard(){
		final Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();
		for(int i=0;i<64;i++) {
			emptyTileMap.put(i,new EmptyTile(i));
		}
		return ImmutableMap.copyOf(emptyTileMap);
		
	}
	//creates Tile- If there is a piece then make an OccupiedTile with Piece and Coordinate provided
	//else make it an empty tile
	public static Tile createTile(final int tileCoordinate,final Piece piece) {
		return piece !=null? new OccupiedTile(tileCoordinate,piece):EMPTY_TILES.get(tileCoordinate);
	}
	
	//constructor
	//Parameter :tileCoordinate - specific tile of the chess board matrix
	private Tile(int tileCoordinate){
		this.tileCoordinate = tileCoordinate;
	}
	
	// returns true is tile is occupied, otherwise return false
	public abstract boolean isTileOccupied();
	public abstract Piece getPiece();
	
	//Child class called Empty Tile which has a Parent class Tile
	//Holds no Pieces since tile is unoccupied
	public static final class EmptyTile extends Tile{
		EmptyTile(int coordinate){
			super(coordinate);
		}
		@Override
		public boolean isTileOccupied() {
			return false;
		}
		
		@Override
		public Piece getPiece() {
			return null;
		}
	}
	
	//Child Class called OccupiedTile which has a Parent Class Tile
	//Holds a piece and is occupied
	public static final class OccupiedTile extends Tile{
		Piece pieceOnTile;
		
		OccupiedTile(int tileCoordinate,Piece pieceOnTile){
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		
		@Override
		public boolean isTileOccupied() {
			return true;
		}
		
		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
	}
}
