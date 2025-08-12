module DNA (toRNA) where
import Data.Maybe
import Data.List(find,head)

dnaToRna :: Char -> Maybe Char
dnaToRna n | n=='G'=Just 'C'
           | n=='C'=Just 'G'
           | n=='T'=Just 'A'
           | n=='A'=Just 'U'
           | otherwise = Nothing 

toRNA :: String -> Either Char String
toRNA xs | isJust badDna = Left (fromJust badDna)
         | otherwise = Right (map (\x -> fromJust(dnaToRna x)) xs) 
         where badDna = find (\x-> isNothing(dnaToRna x)) xs
