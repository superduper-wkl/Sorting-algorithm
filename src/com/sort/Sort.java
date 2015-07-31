package com.sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * 排序类
 * @author wkl
 *
 */
public class Sort {
	
	public static final int begin = 1;
	
	// 最大数量1~50
	public static final int MAXSIZE = 20;

	// 打印输出结果
	public static void print(String title, SqlList list) {
		if (title == null) {
			title = "";
		}
		System.out.println(title);
		for (int i = begin; i < list.length; i++) {
			System.out.print(list.array[i] + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	// 交换对应下标的值
	public static void swap(SqlList list, int l, int s) {
		int temp = list.array[l];
		list.array[l] = list.array[s];
		list.array[s] = temp;
	}
	
	// 讲下标为0置为哨兵或临时变量
	public static SqlList preSort(SqlList list) {
		SqlList temp = new SqlList(new int[list.length + 1]);
		for (int i = 0; i < list.length; i++) {
			temp.array[i + 1] = list.array[i];
		}
		return temp;
	}
	
	// 选择排序
	public static void selectSort(SqlList list) {
		for (int i = begin; i < list.length - 1; i++) {
			int temp = i;
			for (int j = i + 1; j < list.length; j++) {
				if (list.array[j] < list.array[temp]) {
					temp = j;
				}
			}
			if (temp != i) {
				swap(list, i, temp);
			}
		}
		
		print("选择排序", list);
	}
	
	// 冒泡排序
	public static void bubbleSort(SqlList list) {
		for (int i = begin; i < list.length - 1; i++) {
			for (int j = i + 1; j < list.length; j++) {
				if (list.array[i] > list.array[j]) {
					swap(list, i, j);
				}
			}
		}
		
		print("冒泡排序", list);
	}
	
	// 归并排序
	public static void mergeSort(SqlList list) {
		// 划分子序列
		mSort(list, 1, list.length - 1);
		
		print("归并排序", list);
	}
	
	/**
	 * 将序列左右对半划分，最后合并到一起
	 * @param list 排序的数组
	 * @param low 最小下标
	 * @param high 最大小标
	 */
	public static void mSort(SqlList list, int low, int high) {
		// 划分点
		int mid = (low + high) / 2;
		if (low < high) {
			// 左序列
			mSort(list, low, mid);
			// 右序列
			mSort(list, mid + 1, high);
			// 合并
			merge(list, low, mid, high);
		}
	}
	
	/**
	 * 将归并划分的序列进行合并
	 * @param list 排序的数组
	 * @param low 此次合并的最小下标
	 * @param mid 此次合并的划分点
	 * @param high 此次合并的最大下标
	 */
	public static void merge(SqlList list, int low, int mid, int high) {
		// 临时数组
		SqlList temp = new SqlList(new int[high - low + 1]);
		// 左侧起始下标
		int i = low;
		// 右侧起始下标
		int j = mid + 1;
		// 临时数组起始下标
		int k = 0;
		
		// 将左右序列按照从小到大的顺序拷贝到临时数组
		while (i <= mid && j <= high) {
			if (list.array[i] < list.array[j]) {
				temp.array[k++] = list.array[i++];
			} else {
				temp.array[k++] = list.array[j++];
			}
		}
		
		// 将左侧剩余的序列拷贝到临时数组
		while (i <= mid) {
			temp.array[k++] = list.array[i++];
		}
		
		// 将右侧剩余的序列拷贝到临时数组
		while (j <= high) {
			temp.array[k++] = list.array[j++];
		}
		
		// 将临时数组中的数据覆盖到拷贝数组中
		for (int k2 = 0; k2 < temp.length; k2++) {
			list.array[k2 + low] = temp.array[k2];
		}
	}
	
	// 快速排序
	public static void quickSort(SqlList list) {
		qSort(list, begin, list.length - 1);
		
		print("快速排序", list);
	}
	
	public static void qSort(SqlList list, int low, int high) {
		// 枢轴值
		int pivot;
		if (low < high) {
			pivot = partition(list, low, high);
			qSort(list, low, pivot - 1);
			qSort(list, pivot + 1, high);
		}
	}
	
	public static int partition(SqlList list, int low, int high) {
		int pivotkey;
		
		pivotkey = list.array[low];
		while (low < high) {
			while (low < high && list.array[high] >= pivotkey) {
				high--;
			}
			swap(list, low, high);
			
			while (low < high && list.array[low] <= pivotkey) {
				low++;
			}
			swap(list, low, high);
		}
		
		return low;
	}
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("data/test.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String temp;
		int[] a = new int[MAXSIZE];
		for (int i = 0; (temp = br.readLine()) != null && i < MAXSIZE; i++) {
			a[i] = Integer.parseInt(temp);
		}
		
		br.close();
		
		SqlList list1 = new SqlList(a.clone());
		list1 = preSort(list1);
		// 排序前
		print("排序前", list1);
		// 选择排序
		selectSort(list1);
		
		SqlList list2 = new SqlList(a.clone());
		list2 = preSort(list2);
		// 排序前
		print("排序前", list2);
		// 冒泡排序
		bubbleSort(list2);
		
		SqlList list3 = new SqlList(a.clone());
		list3 = preSort(list3);
		// 排序前
		print("排序前", list3);
		// 归并排序
		mergeSort(list3);
		
		SqlList list4 = new SqlList(a.clone());
		list4 = preSort(list4);
		// 排序前
		print("排序前", list4);
		// 快速排序
		quickSort(list4);
		
	}
	
	/**
	 * 排序封装的数组
	 * @author wkl
	 *
	 */
	static class SqlList {
		
		public int[] array;
		
		public int length;

		public SqlList() {}

		public SqlList(int[] array) {
			this.array = array;
			this.length = array.length;
		}
	}
	
}
