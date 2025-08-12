%! create(+DimTuple)
%
% The create/1 predicate succeeds if the DimTuple contains valid chessboard 
% dimensions, e.g. (0,0) or (2,4).
create((DimX, DimY)) :-
	DimX =< 7, DimX >= 0, 
    DimY =< 7, DimY >= 0.

sameRow((FromX, _), (ToX, _)) :-
  FromX =@= ToX.
  
sameColumn((_, FromY), (_, ToY)) :-
  FromY =@= ToY.

onDiagonal((FromX, FromY), (ToX, ToY)) :-
   abs(FromX-ToX,DiffX),
   abs(FromY-ToY,DiffY),
   DiffX=DiffY.
   
%! attack(+FromTuple, +ToTuple)
%
% The attack/2 predicate succeeds if a queen positioned on ToTuple is 
% vulnerable to an attack by another queen positioned on FromTuple.
attack(From,To):-
	sameColumn(From,To);
    sameRow(From,To);
    onDiagonal(From,To).
