package com.Aditya.AI;

public class AI {
	public char[] move(char[] arr, char ret) {
		System.out.println("Running");
		//DIAGONAL  048 check
		if(arr[0] == ret && arr[4] == ret && arr[8] == ' ') {
			arr[8] = ret;
			return arr;
		}
		if(arr[0] == ret && arr[4] == ' ' && arr[8] == ret) {
			arr[4] = ret;
			return arr;
		}
		if(arr[0] == ' ' && arr[4] == ret && arr[8] == ret) {
			arr[0] = ret;
			return arr;
		}
		//DIAGONAL 642 check
		if(arr[6] == ret && arr[4] == ret && arr[2] == ' ') {
			arr[2] = ret;
			return arr;
		}
		if(arr[6] == ret && arr[4] == ' ' && arr[2] == ret) {
			arr[4] = ret;
			return arr;
		}
		if(arr[6] == ' ' && arr[4] == ret && arr[2] == ret) {
			arr[6] = ret;
			return arr;
		}
		//ROW 036 check
		if(arr[0]==ret && arr[3] == ret && arr[6] == ' ') {
			arr[6] = ret;
			return arr;
		}
		if(arr[0]==ret && arr[3] == ' ' && arr[6] == ret) {
			arr[3] = ret;
			return arr;
		}
		if(arr[0]==' ' && arr[3] == ret && arr[6] == ret) {
			arr[0] = ret;
			return arr;
		}
		//ROW 147 check
		if(arr[1] == ret && arr[4] == ret && arr[7] == ' ') {
			arr[7] = ret;
			return arr;
		}
		if(arr[1] == ret && arr[4] == ' ' && arr[7] == ret) {
			arr[4] = ret;
			return arr;
		}
		if(arr[1] == ' ' && arr[4] == ret && arr[7] == ret) {
			arr[1] = ret;
			return arr;
		}
		//ROW 258 check
		if(arr[2]==ret && arr[5] == ret && arr[8] == ' ') {
			arr[8] = ret;
			return arr;
		}
		if(arr[2]==ret && arr[5] == ' ' && arr[8] == ret) {
			arr[5] = ret;
			return arr;
		}
		if(arr[2]==' ' && arr[5] == ret && arr[8] == ret) {
			arr[2] = ret;
			return arr;
		}
		//COLUMN 012 check
		if(arr[0]==ret && arr[1] == ret && arr[2] == ' ') {
			arr[2] = ret;
			return arr;
		}
		if(arr[0]==ret && arr[1] == ' ' && arr[2] == ret) {
			arr[1] = ret;
			return arr;
		}
		if(arr[0]==' ' && arr[1] == ret && arr[2] == ret) {
			arr[0] = ret;
			return arr;
		}
		//COLUMN 345
		if(arr[3]== ret && arr[4]==ret && arr[5]==' ') {
			arr[5] = ret; 
			return arr;
		}
		if(arr[3]== ret && arr[4]==' ' && arr[5]==ret) {
			arr[4] = ret;
			return arr;
		}
		if(arr[3]== ' ' && arr[4]==ret && arr[5]==ret) {
			arr[3] = ret;
			return arr;
		}
		//ROW 678
		if(arr[6]==ret && arr[7] == ret && arr[8] == ' ') {
			arr[8] = ret;
			return arr;
		}
		if(arr[6]==ret && arr[7] == ' ' && arr[8] == ret) {
			arr[7] = ' ';
			return arr;
		}
		if(arr[6]==' ' && arr[7] == ret && arr[8] == ret) {
			arr[6] = ret;
			return arr;
		}
		int value[] = new int[4];
		int choice = 0;
		int ctr=0;
		//combo 1
		if(arr[3]== ' ' && arr[1] == ' ' && arr[5] == ' ') {
			arr[3]=ret;
			return arr;
		}
		if(arr[3]== ret && arr[1] == ' ' && arr[5] == ' ') {
			arr[1]=ret;
			return arr;
		}
		if(arr[3]== ret && arr[1] == ret && arr[5] == ' ') {
			arr[5]=ret;
			return arr;
		}
		if(arr[3]==ret && arr[1] == ret && arr[5] == ret && arr[0] == ' ' && arr[6] == ' ') {
			arr[0]=ret;
			return arr;
		}
		if(arr[3]==ret && arr[1] == ret && arr[5] == ret && arr[2] == ' ' && arr[8] == ' ') {
			arr[2]=ret;
			return arr;
		}
		//combo 2
		if(arr[1]== ' ' && arr[5] == ' ' && arr[7] == ' ') {
			arr[1]=ret;
			return arr;
		}
		if(arr[1]== ret && arr[5] == ' ' && arr[7] == ' ') {
			arr[5]=ret;
			return arr;
		}
		if(arr[1]== ret && arr[5] == ret && arr[7] == ' ') {
			arr[7]=ret;
			return arr;
		}
		if(arr[1]==ret && arr[5] == ret && arr[7] == ret && arr[0] == ' ' && arr[2] == ' ') {
			arr[0]=ret;
			return arr;
		}
		if(arr[1]==ret && arr[5] == ret && arr[7] == ret && arr[6] == ' ' && arr[8] == ' ') {
			arr[6]=ret;
			return arr;
		}
		//combo 3
		if(arr[5]== ' ' && arr[7] == ' ' && arr[3] == ' ') {
			arr[5]=ret;
			return arr;
		}
		if(arr[5]== ret && arr[7] == ' ' && arr[3] == ' ') {
			arr[3]=ret;
			return arr;
		}
		if(arr[5]== ret && arr[7] == ret && arr[3] == ' ') {
			arr[3]=ret;
			return arr;
		}
		if(arr[5]==ret && arr[7] == ret && arr[3] == ret && arr[0] == ' ' && arr[6] == ' ') {
			arr[0]=ret;
			return arr;
		}
		if(arr[5]==ret && arr[7] == ret && arr[3] == ret && arr[6] == ' ' && arr[8] == ' ') {
			arr[6]=ret;
			return arr;
		}
		//combo 4
		if(arr[7]== ' ' && arr[3] == ' ' && arr[1] == ' ') {
			arr[7]=ret;
			return arr;
		}
		if(arr[7]== ret && arr[3] == ' ' && arr[1] == ' ') {
			arr[3]=ret;
			return arr;
		}
		if(arr[7]== ret && arr[3] == ret && arr[1] == ' ') {
			arr[1]=ret;
			return arr;
		}
		if(arr[7]==ret && arr[3] == ret && arr[1] == ret && arr[0] == ' ' && arr[2] == ' ') {
			arr[0]=ret;
			return arr;
		}
		if(arr[7]==ret && arr[3] == ret && arr[1] == ret && arr[6] == ' ' && arr[8] == ' ') {
			arr[6]=ret;
			return arr;
		}
		else {
			for(int i = 0; i < 9; i ++) {
				if(arr[i]==' ') {
					arr[i] = ret;
					return arr;
				}
			}
		}
		return arr;
	}
}
