#include<iostream>
using namespace std::
class Rectangulo{
  private: 
  int base;
  int altura;
  public:
  Rectangulo(int _base, int _altura){
    base = _base;
    altura = _altura;
  }
  ~Rectangulo{}
  int obtenerPerimetro(){
    int perimetro;
    perimetro = base * 2 + altura * 2;
    return perimetro;
  }
}

int main(){
  Rectangulo rectangulo(5,6);
  int unPerimetro = rectangulo.obtenerPerimetro();
  cout<<"El perimetro es "<<unPerimetro<<endl;
  return 0;
}
