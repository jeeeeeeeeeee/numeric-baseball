import java.awt.geom.QuadCurve2D;

public class NumberBaseball {

   private int level;
   public int[] qu;
   private int[] result;

   public int one;
   
   // 난이도가 easy면 3, normal이면 4, hard면 5를 인수로 준다고 가정했습니다.
   public NumberBaseball(int l) {
      level = l;
      qu = new int[level];
      result = new int[level];
   }
   
   int count = 0;
   int number;
   int isthere = 0;
   
   /* question - 숫자 배열을 만들어 리턴 */
   public int[] question() {
      while(count < level){
           isthere = 0;
           number = (int) (Math.random()*10); 
           for(int i=0; i<level; i++){
               if(number == qu[i]){
                   isthere = 1;
                   break;
               }
           }
           if(isthere == 0){
              qu[count] = number;
               count++;
           }
       }
      return qu;
   }

   /* check -  strike ball out 순서대로 담은 1차원 배열 
    * 플레이어가 선택한 숫자 배열을 알려줌 */
   public int[] check(int[] res) {
      result = res;
      int strike=0, ball=0, out=0;
      for(int i = 0; i<level; i++) {
         if(result[i]==qu[i])
            strike ++;
         else {
            for(int j=0; j<level; j++) {
               if(result[i] == qu[j]) {
                  ball++;
                  break;
               }
            }
         }
      }
      out = level - (strike + ball);
      int[] pri = {strike, ball, out};
      return pri;
   }
   public int[] getQuestion() {return qu;}
}