// MathFunctions.pde
// Processing 3.4
// Rens Dur (Project Bèta)

final public float e = 2.718281828;


final public float log10(float a){
  return log(a)/log(10);
}

final public float log20(float a){
  return log(a)/log(20);
}

final public float log50(float a){
  return log(a)/log(50);
}

final public float logBase(float a, float g){
  return log(a)/log(g);
}

final public float ln(float a){
  return log(a)/log(e);
}
