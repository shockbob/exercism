package queenattack
import "errors"
func abs(x int) int {
    if (x >= 0) {
        return x;
    }
    return -x;
}
func CanQueenAttack(w string, b string) (bool, error) {
    if (w == b){
        return false, errors.New("Queens overlap")
    }
    if (w[1] > '8' || b[1] > '8' || w[1] < '1' || b[1] < '1' ||
        w[0] > 'h' || w[0] < 'a' || b[0] > 'h' || b[0] < 'a'){
      return false,errors.New("Coordinates not valid")
    }
    left := w;
    right := b;
    if (w[0] > b[0]){
        left = b
        right = w;
    }
   fileDelta := abs(int(right[0]) - int(left[0]));
   rankDelta  := abs(int(right[1]) - int(left[1]));
   if (fileDelta == 0 || rankDelta == 0){
      return true,nil;
    }
    return fileDelta == rankDelta, nil;
}