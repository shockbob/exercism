"
" Convert a phrase into an uppercased acronym formed from
" the initial letter of each word, ignoring leading underscores
"
" Examples:
"
"   :echo Abbreviate('First In, First Out')
"   FIFO
"
"   :echo Abbreviate('The Road _Not_ Taken')
"   TRNT
"
function! IsAlpha(ch) abort
   return (a:ch >= 'A' && a:ch <= 'Z') || (a:ch >= 'a' && a:ch <= 'z')
endfunction

function! Abbreviate(phrase) abort
  let fixedPhrase = substitute(a:phrase, "-", " ","")
  let phrases = split(fixedPhrase," ")
  let abbrev = ''
  for x in phrases
     let i = 0
     if x[i:i] == '_'
        let i = 1
     endif     
     if (IsAlpha(x[i]))
         let abbrev = abbrev .. toupper(x[i:i])
     endif
  endfor
  return abbrev
endfunction 
