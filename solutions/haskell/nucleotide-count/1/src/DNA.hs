module DNA (nucleotideCounts, Nucleotide(..)) where

import Data.Map (Map,empty,insertWith)
import Data.List (foldr,any)

data Nucleotide = A | C | G | T deriving (Eq, Ord, Show)
mapToNuc :: Char -> Nucleotide
mapToNuc ch |ch == 'A' = A
            |ch =='G' = G
            |ch =='T' = T
            |ch =='C' = C

badNuc :: Char -> Bool
badNuc ch = ch /= 'G' && ch /= 'A' && ch /= 'T' && ch /= 'C'

nucleotideCounts :: String -> Either String (Map Nucleotide Int)
nucleotideCounts xs | any badNuc xs = "Bad nucs"
                    | otherwise =  foldr (\x m -> insertWith (+) x 1 m) empty (map mapToNuc xs) 

