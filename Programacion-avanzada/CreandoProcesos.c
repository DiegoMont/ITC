#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include<sys/wait.h>

int getProcesosACrear();
void crearProcesosHijos(pid_t* pids, int numProcesosHijos);
void esperarAQueLosChamacosTerminen(pid_t* pids, int numProcesosHijos);

pid_t pid = 0;

int main(){
  int numProcesosACrear = getProcesosACrear();
  pid_t* pids = (pid_t*) malloc(sizeof(pid_t) * numProcesosACrear);
  printf("El proceso padre tiene un PID de %d\n", getpid());
  crearProcesosHijos(pids, numProcesosACrear);
  int estamosEnElProcesoPadre = pid != 0;
  if(estamosEnElProcesoPadre)
    esperarAQueLosChamacosTerminen(pids, numProcesosACrear);
}

int getProcesosACrear(){
  int aux;
  printf("Cuántos procesos hijos quiere crear: ");
  scanf("%d", &aux);
  return aux;
}

void crearProcesosHijos(pid_t* pids, int numProcesosHijos){
  for (int i = 0; i < numProcesosHijos; i++){
    pid = fork();
    pids[i] = pid;
    if(pid == -1){
      printf("GG con el hijo %d", i+1);
    } else if(pid == 0){
      printf("Estamos en el proceso hijo con PID = %d y su padre es PPID = %d \n", getpid(), getppid());
      exit(0);
      break;
    }
  }
}

void esperarAQueLosChamacosTerminen(pid_t* pids, int numProcesosHijos){
  for(int i = 0; i < numProcesosHijos; i++){
    pid_t pidHijo = pids[i];
    int estado;
    if(pidHijo == -1)
      continue;
    printf("Esperando a que termine %d\n", pidHijo);
    if(waitpid(pidHijo, &estado, 0) != -1)
      if(WIFEXITED(estado))
        puts("Ya terminó!");
  }
}
