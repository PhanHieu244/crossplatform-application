UPDATE accounts
SET ho = COALESCE(ho, 'No'),
    ten = COALESCE(ten, 'name')
WHERE ho IS NULL OR ten IS NULL;
