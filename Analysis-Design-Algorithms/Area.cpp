/*
This is the name of a famous Portuguese art historian who dedicated a good part of his life to the study of the Lisbon tiles. He devised a theory to determine the amount of tiles required to fill various complex patterns in the floor. To do this, he first calculates the area of a region using a collection of points on a Cartesian plane. Help him to determine such result.
Input
  The first line contains the value of N, the number of points in a region
    The next N lines consist of the x and y coordinates of every point
Output
  The area of the region (rounded to two decimal values)
SAMPLE INPUT:
3
1 1
4 1
4 4
SAMPLE OUTPUT:
4.50
*/
#include<stdio.h>
#include<cmath>

int main() {
  int n;
  scanf("%i", &n);
  int coordenadas[n*2];
  int area = 0;
  for (int i = 0; i < n*2; i++) {
    scanf("%i", coordenadas+i);
    i++;
    scanf("%i", coordenadas+i);
  }
  for (int i = 0; i < n-1; i++) {
    area += coordenadas[i*2] * coordenadas[2*(i+1)+1];
    area -= coordenadas[2*i+1] * coordenadas[2*(i+1)];
  }
  area += coordenadas[n*2-2] * coordenadas[1];
  area -= coordenadas[n*2-1] * coordenadas[0];
  double resultado = area / 2.0;
  printf("%.2f\n", resultado);
}
