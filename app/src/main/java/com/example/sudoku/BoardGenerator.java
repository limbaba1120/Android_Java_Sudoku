package com.example.sudoku;

import java.util.ArrayList;
import java.util.Collections;

/*
	보드 생성
	9 x 9 보드를 3 x 3짜리 보드 9개로 나눔

	s0 s1 s2
	s3 s4 s5
	s6 s7 s8

	s0~8는 각각 3 x 3 행렬

	s0는 1~9의 숫자를 랜덤 배치

	s1 = x2 * s0
	s2 = x1 * s0
	s3 = s0 * x1
	s4 = s1 * x1
	s5 = s2 * x1
	s6 = s0 * x2
	s7 = s1 * x2
	s8 = s2 * x2

	x1 =
	0 0 1
	1 0 0
	0 1 0

	x2 =
	0 1 0
	0 0 1
	1 0 0

 */

public class BoardGenerator {
    private int [][] board = new int[9][9];

    private int [][] x1 = {{0, 0, 1}, {1, 0, 0}, {0, 1, 0}};
    private int [][] x2 = {{0, 1, 0}, {0, 0, 1}, {1, 0, 0}};
    private int [][] s0 = new int [3][3];
    private int [][] s1 = new int [3][3];
    private int [][] s2 = new int [3][3];
    private int [][] s3 = new int [3][3];
    private int [][] s4 = new int [3][3];
    private int [][] s5 = new int [3][3];
    private int [][] s6 = new int [3][3];
    private int [][] s7 = new int [3][3];
    private int [][] s8 = new int [3][3];

    public BoardGenerator() {
        int[][] temp = new int[3][3];

        // 랜덤 순서로 1~9의 숫자 생성
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 9; i++) {
            list.add(i);
        }
        Collections.shuffle(list);  // 랜덤, 이 줄을 없애면 언제나 같은 보드가 나옴

        for(int i = 0; i < 9; i++) {
            s0[i / 3][i % 3] = list.get(i);
        }

        mul(x2, s0, s1);
        mul(x1, s0, s2);
        mul(s0, x1, s3);
        mul(s1, x1, s4);
        mul(s2, x1, s5);
        mul(s0, x2, s6);
        mul(s1, x2, s7);
        mul(s2, x2, s8);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = s0[i][j];
                board[i][j + 3] = s1[i][j];
                board[i][j + 6] = s2[i][j];
                board[i + 3][j] = s3[i][j];
                board[i + 3][j + 3] = s4[i][j];
                board[i + 3][j + 6] = s5[i][j];
                board[i + 6][j] = s6[i][j];
                board[i + 6][j + 3] = s7[i][j];
                board[i + 6][j + 6] = s8[i][j];
            }
        }

    }

    public int[][] get() {
        return board;
    }

    public int get(int row, int col) {
        return board[row][col];
    }

    private void mul(int[][] x, int[][] y, int [][] r) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                r[i][j] = 0;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    r[i][j] += x[i][k] * y[k][j];
                }
            }
        }
    }
}