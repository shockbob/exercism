"
" Tests whether a number is equal to the sum of its digits, 
" each raised to the power of the overall number of digits.
"
function! IsArmstrongNumber(number) abort
   let str_number = string(a:number)
   let len = len(str_number)
   let sum = 0
   for i in range(0,len)
       let result = pow(str2nr(str_number[i:i]),len)
       let sum = sum + result
   endfor
   return sum == a:number
endfunction
