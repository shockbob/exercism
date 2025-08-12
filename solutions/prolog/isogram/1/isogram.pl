isogram(Phrase) :-
  downcase_atom(Phrase,A),
  string_chars(A,Chars),
  include(alnum, Chars, Alnum),
  sort(0,@>=,Alnum,X),
  sort(0,@>,Alnum,Y),
  X =@= Y.

alnum(C) :-  
  char_type(C, alnum).