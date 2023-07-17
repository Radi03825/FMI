main :: IO()
main = do
    print (getNameLengthColor db ((== 'Y'), (> 106)) == [("Pretty Woman",119), ("The Man Who Wasn't There",116), ("Logan's run",120), ("Empire Strikes Back",111), ("Star Trek",132), ("Star Trek: Nemesis",116)])
    print (getNameLengthColor db ((== 'Y'), (> 237)) == [])
    print (getNameLengthColor db ((== 'Y'), (> 238)) == [])
    print (getNameLengthColor db ((== 'N'), (< 133)) == [("Terms of Endearment",132)])
    print (getNameLengthColor db ((== 'N'), (< 300)) == [("Terms of Endearment",132)])

    print ((getStudios db) [2001] == [("USA Entertainm.","Stephen Spielberg"), ("Buzzfeed Entertainm.","Christian Baesler")])
    print ((getStudios db) [2002] == [("Buzzfeed Entertainm.","Christian Baesler")])
    print ((getStudios db) [1990] == [("Disney","Merv Griffin"), ("Buzzfeed Entertainm.","Christian Baesler")])
    print ((getStudios db) [1990, 2001, 1976] == [("Disney","Merv Griffin"), ("USA Entertainm.","Stephen Spielberg"), ("Buzzfeed Entertainm.","Christian Baesler")])
    print ((getStudios db) [1979, 2002] == [("Buzzfeed Entertainm.","Christian Baesler")])

type Title = String
type Year = Int
type Length = Int
type InColor = Char
type StudioName = String

type Name = String
type ProducerID = Int
type Networth = Integer

type Movie = (Title, Year, Length, InColor, StudioName)
type Studio = (Name, ProducerID)
type MovieExec = (Name, ProducerID, Networth)

type MovieDB = ([Movie], [Studio], [MovieExec])

studios :: [Studio]
studios = [("Disney", 199),("USA Entertainm.", 222),("Fox", 333),("Paramount", 123),("MGM", 555),("Buzzfeed Entertainm.", 42)]

movieExecs :: [MovieExec]
movieExecs = [("George Lucas", 555, 200000000), ("Ted Turner", 333, 125000000), ("Stephen Spielberg", 222, 100000000), 
    ("Merv Griffin", 199, 112000000), ("Calvin Coolidge", 123, 20000000), ("Christian Baesler", 42, 420000)]

movies :: [Movie]
movies = [("Pretty Woman", 1990, 119, 'Y', "Disney"), ("The Man Who Wasn't There", 2001, 116, 'Y', "USA Entertainm."), 
    ("Logan's run", 1976, 120, 'Y', "Fox"), ("Star Wars", 1977, -1, 'N', "Fox"), ("Star Wars 2", 1977, 238, 'N', "Fox"), 
    ("Empire Strikes Back", 1980, 111, 'Y',"Fox"), ("Star Trek", 1979, 132, 'Y', "Paramount"), 
    ("Star Trek: Nemesis", 2002, 116, 'Y', "Paramount"), ("Terms of Endearment", 1983,132, 'N', "MGM"), 
    ("The Usual Suspects", 1995, 106, 'Y', "MGM"), ("Gone With the Wind", 1938, 238, 'Y', "MGM"), 
    ("Gone With the Wind 2", 1938,238, 'Y', "MGM"), ("The Fellowship of the Ring", 2001, -1, 'Y', "USA Entertainm.")]

db :: MovieDB
db = (movies, studios, movieExecs)



--Task 1
getNameLengthColor :: MovieDB -> ((Char -> Bool), (Int -> Bool)) -> [(Title, Length)]
getNameLengthColor (ms, _, _) = \ (f, g) -> helper ms f g (getMaxLengthMovie [(t, y, l, c, s) | (t, y, l, c, s) <- ms, c == 'Y'])
 where
    helper ms f g (_, _, maxLength, _, _) = [(t, l) | (t, _, l, c, _) <- ms, (f c) && (g l) && l > 0 && l < maxLength]

getMaxLengthMovie :: [Movie] -> Movie
getMaxLengthMovie ms = foldr1 helper ms
 where
    helper :: Movie -> Movie -> Movie
    helper m1@(_, _, l1, _, _)  m2@(_, _, l2, _, _)
     | l1 >= l2 = m1
     | otherwise = m2



--Task 2
getStudios :: MovieDB -> ([Year] -> [(StudioName, Name)])
getStudios (ms, ss, mE) = \ ys -> [(n, getProducerName p mE) | (n, p) <- getStudioWithMoviesInOneYear ss ms, filmOfStudioIsInYears n ms ys] 
                                    ++ [(s, getProducerName p mE) | (s, p) <- getStudiosWithoutMovies ss ms]

getStudiosWithoutMovies :: [Studio] -> [Movie] -> [Studio]
getStudiosWithoutMovies ss ms = [(n, p) | (n, p) <- ss, helper n ms]
 where
    helper n ms = [m | m@(_, _, _, _, sN) <- ms, sN == n] == []

getStudioWithMoviesInOneYear :: [Studio] -> [Movie] -> [Studio]
getStudioWithMoviesInOneYear ss ms = [(sN, p) | (sN, p) <- ss, helper sN ms 0 /= 0]
 where
    helper _ [] yr = yr
    helper sN ((_, y, _, _, s):ms) yr
     | sN == s && (yr == 0 || y == yr) = helper sN ms y
     | sN == s && y /= yr = 0
     | otherwise = helper sN ms yr

filmOfStudioIsInYears :: Name -> [Movie] -> [Year] -> Bool
filmOfStudioIsInYears _ [] _ = False
filmOfStudioIsInYears _ _ [] = False
filmOfStudioIsInYears n ((_, yr, _, _, sN):ms) ys
 | n == sN = elem yr ys
 | otherwise = filmOfStudioIsInYears n ms ys
 
getProducerName :: ProducerID -> [MovieExec] -> Name
getProducerName _ [] = "Unknown"
getProducerName pId ((n, p, _):mE)
 | pId == p = n
 | otherwise = getProducerName pId mE


